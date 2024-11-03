package visitor;

public class Main {
    public static void main(String[] args) {

        Directory root = new Directory("root");
        Directory subDir1 = new Directory("subDir1");
        Directory subDir2 = new Directory("subDir2");

        File file1 = new File("file1.txt", 10);
        File file2 = new File("file2.jpg", 5);
        File file3 = new File("file3.txt", 15);
        File file4 = new File("file4.docx", 20);

        root.addElement(file1);
        root.addElement(subDir1);
        subDir1.addElement(file2);
        subDir1.addElement(file3);
        root.addElement(subDir2);
        subDir2.addElement(file4);

        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);
        System.out.println("Total size of files: " + sizeVisitor.getTotalSize() + " MB");

        SearchVisitor searchVisitor = new SearchVisitor("file");
        root.accept(searchVisitor);
        System.out.println("Files found with 'file' in the name:");
        for (File file : searchVisitor.getFoundFiles()) {
            System.out.println(file.getName() + " (" + file.getSize() + " MB)");
        }
    }
}
