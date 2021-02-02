/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.sistemas.akka05;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 *
 * @author Hasler
 */
public class SumaSegmentos extends UntypedAbstractActor {

    private double vec[];
    private double total;
    private int segme;

    @Override
    public void preStart() throws Exception {
        super.preStart();

    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Double) {
            total += (Double) message;
            segme--;
            if (segme == 0) {

                System.out.println("La suma total es " + total);
                this.getContext().stop(getSelf());
            }
        } else if (message instanceof double[]) {
            total = 0;
            segme = 10;
            vec = (double[]) message;
            Sumar sumar[] = new Sumar[10];
            ActorRef sumador[] = new ActorRef[10];

            for (int i = 0; i < 10; i++) {
                sumar[i] = new Sumar();
                sumar[i].vec = vec;
                sumar[i].ini = (int) (i / 10.0 * sumar[i].vec.length);
                sumar[i].fin = (int) ((i + 1) / 10.0 * sumar[i].vec.length);
                sumador[i] = this.getContext().actorOf(
                        Props.create(Sumador.class),
                        "Sumador" + i
                );
                sumador[i].tell(
                        sumar[i],
                        this.getSelf()
                );
            }
        }
    }

}
