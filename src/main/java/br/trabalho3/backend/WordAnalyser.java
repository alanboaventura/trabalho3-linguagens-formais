package br.trabalho3.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por analisar o texto inserido pelo usuário e discernir
 * quais tipos de palavras estão contidos no mesmo.
 */
public class WordAnalyser {

    public static String analyseText(String text) throws LexicalError {
        Map<Integer, String> mWordType = initializeMapWordType();

        int numLine = 0;
        StringBuilder resultado = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        resultado.append("dados analisados").append(lineSeparator);
        Lexico lexico = new Lexico();
        Token token;
        for (String line : text.split("\n|\n\r")) {
            numLine++;

            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            lexico.setInput(line);
            try {
                token = lexico.nextToken();
                while (token != null) {
                    resultado.append(numLine).append("\t").append(token.getLexeme()).append("\t").append(mWordType.get(token.getId())).append(lineSeparator);
                    token = lexico.nextToken();
                }
            } catch (LexicalError e) {
                if (e.getMessage() != null) {
                    throw new LexicalError("erro na linha " + numLine + " - " + e.getMessage());
                } else {
                    throw e;
                }
            }
        }

        return resultado.toString();
    }

    private static Map<Integer, String> initializeMapWordType() {
        Map<Integer, String> mWordType = new HashMap<>();

        mWordType.put(2, "motor");
        mWordType.put(3, "ano");
        mWordType.put(4, "valor");
        mWordType.put(5, "km");
        mWordType.put(6, "chassi");
        mWordType.put(7, "placa");
        mWordType.put(8, "marca ou modelo");
        mWordType.put(9, "combustível");
        mWordType.put(10, "combustível");
        mWordType.put(11, "combustível");
        mWordType.put(12, "combustível");

        return mWordType;
    }
}