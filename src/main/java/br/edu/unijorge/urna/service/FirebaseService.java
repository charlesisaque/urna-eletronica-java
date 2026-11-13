package br.edu.unijorge.urna.service;

import br.edu.unijorge.urna.model.Voto;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

public class FirebaseService {
    private Firestore db;
    private boolean conectado = false;

    public FirebaseService() {
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            this.db = FirestoreClient.getFirestore();
            this.conectado = true;
            System.out.println("[Firebase] Conectado ao Cloud Firestore com sucesso.");
        } catch (Exception e) {
            System.err.println("[Firebase] Aviso: Rodando em modo offline (serviceAccountKey.json não encontrado).");
        }
    }

    public void registrarVoto(Voto voto) {
        if (conectado && db != null) {
            // Envio assíncrono para não congelar a tela Swing
            db.collection("votos_urna_cidada").add(voto.toMap());
        } else {
            System.out.println("[LOG OFFLINE] Voto registrado: " + voto.toMap());
        }
    }
}