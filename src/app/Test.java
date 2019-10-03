package app;

import contracts.Command;

public class Test {

    public static void main(String[] args) {
        var helloWorld = new Command() {

            public void Action(Object... objects) {
                System.out.println("Hello command");
            }

            public void Reverse(Object... objects) {

            }
        };

        helloWorld.Action();
    }

}
