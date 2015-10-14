package application;

import application.model.Fassade;
import presentation.controller.UIController;
import presentation.view.UIConsole;

public class GameMain
{

    public static void main( String[] args )
    {
        //TODO remove "controller" from UIConsole constuctor and create contoller object in class UIconsole
        Fassade fassade = new Fassade();
        UIController controller = new UIController(fassade);
        new UIConsole( controller, fassade );

    }
}
