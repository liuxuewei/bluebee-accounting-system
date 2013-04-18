package com.bluebee.ui.frame;

import com.bluebee.ui.widget.CurtainButton;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class CurtainPane extends JLayeredPane
{
  private static final long serialVersionUID = -9144760003634631239L;
  private static double DELTA = 0.1D;
  private static int ANIMATION_INTERVAL = 15;

  private ArrayList<CurtainDrawer> drawers = new ArrayList();
  private int selectedPane;
  private int currentLayer = -30000;
  private CurtainPaneLayout curtain_layout;
  private boolean animated;

  public CurtainPane()
  {
    this.curtain_layout = new CurtainPaneLayout();
    setLayout(this.curtain_layout);
  }

  public void addPane(String title, Component comp) {
    addPane(title, null, comp, true);
  }

  public void addPane(String title, Icon icon, Component comp, boolean visible) {
    CurtainButton cpb = new CurtainButton(title, icon);
    CurtainDrawer drawer = new CurtainDrawer(cpb, comp);
    cpb.addActionListener(new ExtendingAction(drawer));
    add(drawer, new Integer(this.currentLayer++));
    this.drawers.add(drawer);
    this.selectedPane += 1;
    comp.setVisible(visible);
    cpb.setVisible(visible);
  }

  public void setSelectedPane(int selectedIndex) {
    this.selectedPane = selectedIndex;
    doLayout();
  }

  private void resetLayer(int n) {
    setLayout(this.curtain_layout);
    setSelectedPane(n);
  }

  public boolean isAnimated() {
    return this.animated;
  }

  public void setAnimated(boolean animated) {
    this.animated = animated;
  }

  class AnimationAction
    implements ActionListener
  {
    private CurtainPane.AnimationLayout layout;
    private Timer timer;
    private int expandingPane;
    private double ratio = 0.0D;

    public AnimationAction(Timer timer,int expandingPaneIndex) {
      this.timer = timer;
      this.expandingPane = expandingPaneIndex;
      this.layout = new AnimationLayout(this.ratio, this.expandingPane);
      CurtainPane.this.setLayout(this.layout);
    }

    public void actionPerformed(ActionEvent e) {
      this.ratio += CurtainPane.DELTA;
      if (this.ratio > 1.0D) {
        CurtainPane.this.resetLayer(this.expandingPane);
        this.timer.stop();
        this.timer = null;
      } else {
        this.layout.setRatio(this.ratio);
        CurtainPane.this.doLayout();
      }
    }
  }

  class AnimationLayout extends Layout
  {
    private double ratio;
    private int expandingPane;

    public AnimationLayout(double ratio, int expandingPaneIndex)
    {
      super();
      this.ratio = ratio;
      this.expandingPane = expandingPaneIndex;
    }

    public void setRatio(double ratio) {
      this.ratio = ratio;
    }

    protected int layoutDrawer(CurtainDrawer drawer, int i, int y, int w, int h)
    {
      if (CurtainPane.this.selectedPane == i) {
        int delta = (int)(h - (h - 22) * 
          this.ratio);
        drawer.setBounds(0, y, w, h);
        return y + delta;
      }if (this.expandingPane == i) {
        int delta = (int)((h - 22) * this.ratio) + 
          22;
        drawer.setBounds(0, y, w, h);
        return y + delta;
      }
      drawer.setBounds(0, y, w, h);
      return y + 22;
    }
  }

  class CurtainDrawer extends JComponent
  {
    private static final long serialVersionUID = 4837570861205894451L;

    public CurtainDrawer(CurtainButton cpb, Component comp)
    {
      setLayout(new BorderLayout());
      add(cpb, "North");
      add(comp, "Center");
    }
  }

  class CurtainPaneLayout extends CurtainPane.Layout
  {
    CurtainPaneLayout()
    {
      super();
    }
    protected int layoutDrawer(CurtainPane.CurtainDrawer drawer, int i, int y, int w, int h) {
      drawer.setBounds(0, y, w, h);
      if (CurtainPane.this.selectedPane == i) {
        return y + h;
      }
      return y + 22;
    }
  }

  class ExtendingAction
    implements ActionListener
  {
    private CurtainPane.CurtainDrawer drawer;
    private Timer timer;

    public ExtendingAction(CurtainPane.CurtainDrawer cd)
    {
      this.drawer = cd;
    }

    public void actionPerformed(ActionEvent e) {
      int layer = CurtainPane.this.drawers.indexOf(this.drawer);
      if (layer != CurtainPane.this.selectedPane)
        if (CurtainPane.this.isAnimated()) {
          if ((this.timer != null) && (this.timer.isRunning()))
            this.timer.stop();
          this.timer = new Timer(CurtainPane.ANIMATION_INTERVAL, null);
          ActionListener action = new AnimationAction(this.timer, layer);
          this.timer.addActionListener(action);
          this.timer.start();
        } else {
          CurtainPane.this.setSelectedPane(layer);
        }
    }
  }

  abstract class Layout
    implements LayoutManager
  {
    Layout()
    {
    }

    public void addLayoutComponent(String name, Component comp)
    {
    }

    public void removeLayoutComponent(Component comp)
    {
    }

    public Dimension preferredLayoutSize(Container target)
    {
      return new Dimension(140, 0);
    }
    public Dimension minimumLayoutSize(Container parent) {
      return new Dimension(140, 0);
    }
    public void layoutContainer(Container parent) {
      int size = CurtainPane.this.drawers.size();
      int h = parent.getHeight() - (size - 1) * 22;
      int w = parent.getWidth();
      int y = 0;
      for (int i = 0; i < size; i++) {
        CurtainPane.CurtainDrawer drawer = (CurtainPane.CurtainDrawer)CurtainPane.this.drawers.get(i);
        y = layoutDrawer(drawer, i, y, w, h);
      }
    }

    protected abstract int layoutDrawer(CurtainPane.CurtainDrawer paramCurtainDrawer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}