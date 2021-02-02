package pe.edu.unmsm.sistemas.akka05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.io.IOException;

/**
 *
 * @author Hasler
 */
public class Main {
    //Con N=10000 datos, sale mas exacto el calculo de PI
    int N = 1000;
    double vec[] = new double[N];

    public static void main(String[] args) throws IOException {

        Main main1 = new Main();
        main1.inicializar();
        ActorSystem system = ActorSystem.create("suma-vector");
        ActorRef sumavector1 = system.actorOf(Props.create(SumaSegmentos.class));
        sumavector1.tell(main1.vec, ActorRef.noSender());
        system.terminate();
    }

    private void inicializar() throws IOException {
        vec = GenerarElementosPI();
    }

    public double[] GenerarElementosPI() {
        double aux = 0.0;
        double sum = 0.0;
        double arreglo[] = new double[N];
        double factor = 1.0;
        for (int k = 0; k < N; k++) {
            if (k % 2 == 0) {
                factor = 1.0;
            } else {
                factor = -1.0;
            }

            arreglo[k] = 4 * (factor / (2 * k + 1));

        }
        return arreglo;
    }

}
