package com.pc.global.car.renting.customeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response
{
    private Object data;
    private HttpStatus status;
    private String reason;

    public Response(Object data)
    {
        this.status = HttpStatus.OK;
        this.data = data;
        this.reason = "success";
    }

    public Response(HttpStatus httpStatus, String reason)
    {
        this.status = httpStatus;
        this.reason = reason;
    }
}
