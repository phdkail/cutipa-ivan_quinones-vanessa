package com.backend.test;

import com.backend.dbconnection.H2Connection;

    public class Application{
        public static void main(String[] args) {
            H2Connection.ejecutarScriptInicial();}
    }