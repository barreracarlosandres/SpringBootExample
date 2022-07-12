package com.example.demo.common.infrastructure.exception;

public final class PostMessageExeptions {
    public static final String POST_NO_EXISTE = "Post no existe";
    public static final String YA_EXISTE_EL_POST = "Ya existe el Post";
    public static final String ID_POST_DEBE_SER_MAYOR_QUE_0 = "idPost debe ser mayor que 0";
    public static final String EL_TITULO_NO_PUEDE_SER_NULO = "El titulo no puede ser nulo";
    public static final String EL_CUERPO_DEL_POST_NO_PUEDE_SER_NULO = "El cuerpo del post no puede ser nulo";

    private PostMessageExeptions() {
    }
}
