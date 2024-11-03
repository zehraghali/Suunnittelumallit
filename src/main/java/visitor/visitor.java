package visitor;

import java.util.ArrayList;
import java.util.List;

interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
}

class File implements FileSystemElement {
    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

// Directory class
class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> elements;

    public Directory(String name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addElement(FileSystemElement element) {
        elements.add(element);
    }

    public List<FileSystemElement> getElements() {
        return elements;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

interface FileSystemVisitor {
    void visit(File file);
    void visit(Directory directory);
}

class SizeCalculatorVisitor implements FileSystemVisitor {
    private int totalSize;

    public int getTotalSize() {
        return totalSize;
    }

    @Override
    public void visit(File file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }
}

class SearchVisitor implements FileSystemVisitor {
    private final String searchTerm;
    private final List<File> foundFiles;

    public SearchVisitor(String searchTerm) {
        this.searchTerm = searchTerm;
        this.foundFiles = new ArrayList<>();
    }

    public List<File> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public void visit(File file) {
        if (file.getName().contains(searchTerm)) {
            foundFiles.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }
}

