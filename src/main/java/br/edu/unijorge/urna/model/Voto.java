package br.edu.unijorge.urna.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Voto {
    private String cargo;
    private String numeroDigitado;
    private String tipoVoto; // "NOMINAL", "BRANCO" ou "NULO"
    private String timestamp;

    public Voto(String cargo, String numeroDigitado, String tipoVoto) {
        this.cargo = cargo;
        this.numeroDigitado = numeroDigitado;
        this.tipoVoto = tipoVoto;
        this.timestamp = Instant.now().toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("cargo", cargo);
        map.put("numeroDigitado", numeroDigitado);
        map.put("tipoVoto", tipoVoto);
        map.put("timestamp", timestamp);
        return map;
    }
}