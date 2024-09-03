package abstract_factory;

abstract class UIElement {
    protected String text;

    public UIElement(String text) {
        this.text = text;
    }

    public abstract void display();

    public void setText(String text) {
        this.text = text;
    }
}

// Button abstract class
abstract class Button extends UIElement {
    public Button(String text) {
        super(text);
    }
}


abstract class TextField extends UIElement {
    public TextField(String text) {
        super(text);
    }
}

abstract class Checkbox extends UIElement {
    public Checkbox(String text) {
        super(text);
    }
}



class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("[ Button A: " + text + " ]");
    }
}

class TextFieldA extends TextField {
    public TextFieldA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("+------------+");
        System.out.println("| " + text + " |");
        System.out.println("+------------+");
    }
}

class CheckboxA extends Checkbox {
    public CheckboxA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("[ ] Checkbox A: " + text);
    }
}



class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("<< Button B: " + text + " >>");
    }
}

class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("****************");
        System.out.println("* " + text + " *");
        System.out.println("****************");
    }
}

class CheckboxB extends Checkbox {
    public CheckboxB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("[x] Checkbox B: " + text);
    }
}


abstract class UIFactory {
    public abstract Button createButton(String text);
    public abstract TextField createTextField(String text);
    public abstract Checkbox createCheckbox(String text);
}

class AFactory extends UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonA(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldA(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxA(text);
    }
}

class BFactory extends UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonB(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldB(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxB(text);
    }
}

public class Main {
    public static void main(String[] args) {

        UIFactory factoryA = new AFactory();

        Button buttonA = factoryA.createButton("Click Me!");
        TextField textFieldA = factoryA.createTextField("Enter Text");
        Checkbox checkboxA = factoryA.createCheckbox("Accept Terms");

        System.out.println("Style A:");
        buttonA.display();
        textFieldA.display();
        checkboxA.display();

        UIFactory factoryB = new BFactory();

        Button buttonB = factoryB.createButton("Submit");
        TextField textFieldB = factoryB.createTextField("Type Here");
        Checkbox checkboxB = factoryB.createCheckbox("I Agree");

        System.out.println("\nStyle B:");
        buttonB.display();
        textFieldB.display();
        checkboxB.display();

        buttonA.setText("New Button A Text");
        System.out.println("\nUpdated Style A Button:");
        buttonA.display();
    }
}
