package com.br.panacademy.devcompilers.bluebank.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class AwsLambdaService implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        HashMap<String, String> json = new HashMap<>();

        json.put("message", "Lambda AWS - Squad 8.");

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        writer.write(gson.toJson(json));
        writer.close();
    }
}
