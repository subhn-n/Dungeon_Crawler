import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {
    private List<Displayable> renderList;

    public RenderEngine(){
        renderList = new ArrayList<>();
    }

    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable d){
        renderList.add(d);
    }

    @Override
    public void update(){
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        renderList.forEach(v->v.draw(g));
    }

}
