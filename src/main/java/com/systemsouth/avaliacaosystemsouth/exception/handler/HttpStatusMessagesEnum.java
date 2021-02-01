package com.systemsouth.avaliacaosystemsouth.exception.handler;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public enum HttpStatusMessagesEnum implements Describable {
    BAD_REQUEST(HttpStatus.BAD_REQUEST){
        @Override
        public Integer codeMessage() {
            return 5;
        }
    },NOT_FOUND(HttpStatus.NOT_FOUND){
        @Override
        public Integer codeMessage() {
            return 6;
        }
    },INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR){
        @Override
        public Integer codeMessage() {
            return 7;
        }
    },UNAUTHORIZED(HttpStatus.UNAUTHORIZED){
        @Override
        public Integer codeMessage() {
            return 10;
        }
    },CONFLICT(HttpStatus.CONFLICT){
        @Override
        public Integer codeMessage() {
            return 12;
        }
    };

    private HttpStatus status;

    HttpStatusMessagesEnum(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static HttpStatusMessagesEnum getHttpStatusMessageFromHttpStatus(HttpStatus status) {
        return Arrays.asList(HttpStatusMessagesEnum.values())
                .stream()
                .filter(httpStatus -> httpStatus.getStatus() == status)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}

interface Describable {

    /** Informar o código de mensagem no properties que informará mais detalhes a respeito do erro */
    Integer codeMessage();
}

