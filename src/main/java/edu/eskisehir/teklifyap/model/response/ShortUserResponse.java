package edu.eskisehir.teklifyap.model.response;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUserResponse {

    public ShortUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.mail = user.getMail();
        this.creationDate = user.getCreationDate();
        this.confirmed = user.isConfirmed();
        if (user.getMaterialList() != null)
            this.materialList = user.getMaterialList();
    }

    private int id;
    private String name;
    private String surname;
    private String mail;
    private LocalDateTime creationDate;
    private boolean confirmed;
    private List<Material> materialList;
}
