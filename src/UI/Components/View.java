package UI.Components;

import static org.fusesource.jansi.Ansi.ansi;

public class View {
    private String text;
    private String[] mutateText;
    private int width, height = 3, x, y=1;
    private Borders borders;
    private ColorScheme colorScheme;

    public View(String text, Borders borders, ColorScheme colorScheme){
        setText(text);
        this.borders = borders;
        this.colorScheme = colorScheme;
    }

    public void draw(){
        System.out.print(ansi()
                .bgRgb(colorScheme.getBgBorderRgb())
                .fgRgb(colorScheme.getFgBorderRgb()));
        borders.draw(x, y, height, width+2);
        int index = 1;
        for (var line :
                mutateText) {
            System.out.print(ansi()
                    .bgRgb(colorScheme.getBgTextRgb())
                    .fgRgb(colorScheme.getFgTextRgb())
                    .cursor(y+index, x+3)
                    .a(line));
            index++;
        }
        System.out.print(ansi().fgDefault().bgDefault());
    }

    private void refreshMutateText(){
        mutateText = text.split("\n");
    }

    private int getCountOfLines(){
        int maxLength = 0;
        for (var line :
                mutateText) {
            if(maxLength < line.length()) maxLength = line.length();
        }

        return maxLength;
    }

    private void refreshWidth(){
        int maxLength = getCountOfLines();
        if(width - 2 < maxLength) width = maxLength + 2;
    }
    private void refreshHeight(){
        int data = 1;
        for (var symbol :
                text.toCharArray()) {
            if(symbol == '\n') data++;
        }
        if(data > height-2) height = data+2;
    }

    public void setWidth(int width) {
        refreshWidth();
        this.width = width;
    }

    public void setHeight(int height) {
        if(height < 3) return;
        refreshWidth();
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBorders(Borders borders) {
        this.borders = borders;
    }

    public Borders getBorders() {
        return borders;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        refreshMutateText();
        refreshWidth();
        refreshHeight();
    }
}
