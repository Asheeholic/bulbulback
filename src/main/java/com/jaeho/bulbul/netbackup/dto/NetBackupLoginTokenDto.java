package com.jaeho.bulbul.netbackup.dto;

import lombok.Data;

@Data
public class NetBackupLoginTokenDto {
//    {
//        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJuYnVfbWFzdGVyIiwianRpIjoiOTViNzMyMTQtZmNjMy00Nzc1LTgxYWQtNDZhZWMxMzU1ZWRlIiwiaWF0IjoxNjg2NDgwNDIwNzI0LCJleHAiOjE2ODY1NjY4MjA3MjQsInN1YiI6Im5idTEwOnJvb3Q6dW5peHB3ZCIsImF1dGhfdG9rZW4iOiI5NWI3MzIxNC1mY2MzLTQ3NzUtODFhZC00NmFlYzEzNTVlZGUiLCJpc19hZG1pbiI6InRydWUiLCJpc19tYWNoaW5lIjoiZmFsc2UiLCJ0b2tlbl90eXBlIjoiU0VTU0lPTiJ9.aiy-gSygSdJaouy1mdVHH5c1H1WLWKUXbyNXrz4O4DKm9vjCxTws0yFpUA-or2wcS6nUTJyVPOpgrrz0jzFnS8THQpTEE-eJEYybluILqGhQCu-OQ8fQ6xUuf7CldZolV2ZTvZDKI9DNbcQ1wvhy5bMutfvRHZZUfI-XLc5H4Et-reHL-xVv-IX3VmYuaLXcXsGvIX8kklngfsP1zNUCAcfYH3OrC0s8I1k5AQXyTIAMy_eYbrfrNCgxNw5X_x_rwf8MsvIWsj1EZtR08G2Rx2xBaUQVqf8JdGKJ5uwIQ2TmjaeXi0DZ_nO-fYuw1V8IcWEb3wC45aAn2vyJTS-Lpg",
//            "tokenType": "BEARER",
//            "validity": 86400
//    }
    private String token;
    private String tokenType;
    private int validity;

}
