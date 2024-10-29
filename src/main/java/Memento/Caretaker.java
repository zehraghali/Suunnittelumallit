package Memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Caretaker {
    private Stack<Memento> undoStack = new Stack<>();
    private Stack<Memento> redoStack = new Stack<>();

    public void save(Memento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }

    public Memento undo() {
        if (!undoStack.isEmpty()) {
            Memento memento = undoStack.pop();
            redoStack.push(memento);
            return undoStack.isEmpty() ? null : undoStack.peek();
        }
        return null;
    }

    public Memento redo() {
        if (!redoStack.isEmpty()) {
            Memento memento = redoStack.pop();
            undoStack.push(memento);
            return memento;
        }
        return null;
    }

    public List<Memento> getUndoStack() {
        return new ArrayList<>(undoStack);
    }
}

