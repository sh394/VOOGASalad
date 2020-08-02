package voogasalad.utilities.frontEndCommands.status;

import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

public class UpdateCoin extends FrontEndCommand {
    private static final String METHOD_NAME = "setStatus";
    private static final String COIN = "Coin";

    private int coin;

    public UpdateCoin(int coin) {
        this.coin = coin;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, String.class, Integer.TYPE);
            m.invoke(getEngineController(), COIN, coin);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
