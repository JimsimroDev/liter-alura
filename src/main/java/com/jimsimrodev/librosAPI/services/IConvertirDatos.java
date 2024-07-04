package com.jimsimrodev.librosAPI.services;

public interface IConvertirDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
