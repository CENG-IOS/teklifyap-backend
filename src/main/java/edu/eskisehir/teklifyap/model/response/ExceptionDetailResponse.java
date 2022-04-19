package edu.eskisehir.teklifyap.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionDetailResponse {

    private final Long timestamp = System.currentTimeMillis();
    private final Integer status;
    private final String error;
    private final String path;

}