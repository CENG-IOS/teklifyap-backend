package edu.eskisehir.teklifyap.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import edu.eskisehir.teklifyap.model.MaterialTableRow;
import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.ShortOfferMaterial;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PdfGenerateService {

    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private final TemplateEngine templateEngine;

    public byte[] generateOfferPdf(Offer offer, List<ShortOfferMaterial> materialList) {

        List<MaterialTableRow> materialTableRowList = new LinkedList<>();

        double sgk = 0;
        double total = 0;
        double kdv = 0;
        double overallTotal = 0;
        String date = offer.getDate().format(DateTimeFormatter.ofPattern("d.MM.uuuu"));

        for (int i = 0; i < materialList.size(); i++) {
            if (!materialList.get(i).getMaterial().getName().equals("SGK Stopaj Bedeli")) {
                MaterialTableRow row = new MaterialTableRow();
                row.setNo(i + 1);
                row.setName(materialList.get(i).getMaterial().getName());
                row.setUnit(materialList.get(i).getMaterial().getUnit());
                row.setPricePerUnit(formatter.format(materialList.get(i).getMaterial().getPricePerUnit()).replace("TRY","").replace(".",","));
                row.setProfitRate((offer.getProfitRate() + 100) / 100);
                row.setUnitPrice(formatter.format(materialList.get(i).getUnitPrice()).replace("TRY","").replace(".",","));
                row.setTotal(formatter.format(materialList.get(i).getMaterial().getPricePerUnit() * row.getProfitRate() * materialList.get(i).getUnitPrice()).replace("TRY","").replace(".",","));
                total += materialList.get(i).getMaterial().getPricePerUnit() * row.getProfitRate() * materialList.get(i).getUnitPrice();
                materialTableRowList.add(row);
            } else {
                sgk = materialList.get(i).getUnitPrice();
            }
        }

        kdv = total * 0.18;
        overallTotal = kdv + total + sgk;

        Context context = new Context();
        context.setVariable("offer", offer);
        context.setVariable("materials", materialTableRowList);
        context.setVariable("sgk", formatter.format(sgk).replace("TRY","").replace(".",","));
        context.setVariable("total", formatter.format(total).replace("TRY","").replace(".",","));
        context.setVariable("kdv", formatter.format(kdv).replace("TRY","").replace(".",","));
        context.setVariable("overall", formatter.format(overallTotal).replace("TRY","").replace(".",","));
        context.setVariable("date", date);
        String orderHtml = templateEngine.process("teklif", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        return target.toByteArray();
    }
}
