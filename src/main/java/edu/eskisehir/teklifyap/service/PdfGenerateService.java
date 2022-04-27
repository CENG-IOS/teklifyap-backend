package edu.eskisehir.teklifyap.service;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.ShortOfferMaterial;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PdfGenerateService {

    public void generatePdfFile(Offer offer, List<ShortOfferMaterial> materialList) throws Exception {

        if (materialList.size() > 15)
            throw new Exception("TooManyItemsException");

        try (XSSFWorkbook wb = new XSSFWorkbook(Files.newInputStream(Paths.get("teklif.xlsx")))) {

            XSSFSheet workSheet = wb.getSheetAt(0);

            Row row2 = workSheet.getRow(2);
            row2.getCell(3).setCellValue("SAYIN " + offer.getCompanyName().toUpperCase() + " YETKİLİSİ");

            Row row4 = workSheet.getRow(4);
            row4.getCell(3).setCellValue(offer.getTitle());

            double total = 0;

            for (int i = 0; i < materialList.size(); i++) {

                Row row = workSheet.getRow(i + 7);
                row.getCell(2).setCellValue(i + 1);
                row.getCell(3).setCellValue(materialList.get(i).getMaterial().getName());
                row.getCell(4).setCellValue(materialList.get(i).getMaterial().getPricePerUnit());
                row.getCell(5).setCellValue(materialList.get(i).getMaterial().getUnit());
                row.getCell(6).setCellValue(materialList.get(i).getUnitPrice());
                row.getCell(7).setCellValue(offer.getProfitRate());
                row.getCell(8).setCellValue(materialList.get(i).getMaterial().getPricePerUnit() * materialList.get(i).getUnitPrice() * offer.getProfitRate());
                total += materialList.get(i).getMaterial().getPricePerUnit() * materialList.get(i).getUnitPrice() * offer.getProfitRate();
            }

            int last = materialList.size() + 7;

            Row row = workSheet.getRow(23);
            row.getCell(8).setCellValue(100000);

            Row row1 = workSheet.getRow(24);
            row1.getCell(8).setCellValue(total);

            double kdv = total * 0.18;
            Row row3 = workSheet.getRow(25);
            row3.getCell(8).setCellValue(kdv);

            Row row5 = workSheet.getRow(26);
            row5.getCell(8).setCellValue(kdv + total+100000);

            wb.write(Files.newOutputStream(Paths.get("c.xlsx")));
        }

        Workbook workbook = new Workbook("c.xlsx");
        workbook.save("a.pdf", SaveFormat.PDF);

        PdfReader reader = new PdfReader("a.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
                Files.newOutputStream(Paths.get("b.pdf"))); // output PDF

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            PdfContentByte over = stamper.getOverContent(i);

            over.setRGBColorStroke(0xFF, 0xFF, 0xFF);
            over.setLineWidth(20f);
            over.rectangle(0, 830, 500, 850);
            over.stroke();
        }
        stamper.close();
    }
}
