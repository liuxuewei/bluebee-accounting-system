package com.bluebee.ui.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class PageContainer extends JPanel
  implements AncestorListener
{
  private static PageContainer thisPage = new PageContainer();
  private static JPanel titleControlPanel;
  private static JPanel pageContainer;
  private static CardLayout card;
  private static JLabel titleLabel;
  private IPage defaultPage;

  private PageContainer()
  {
    setLayout(new BorderLayout());

    add(getTitleControlPanel(), "North");

    add(getPageContainer(), "Center");
  }

  public static PageContainer getInstance() {
    return thisPage;
  }

  public JPanel getTitleControlPanel() {
    if (titleControlPanel == null) {
      titleControlPanel = new JPanel();
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      titleControlPanel.setLayout(gridbag);

      int top = 2;
      int left = 1;
      int bottom = 2;
      int right = 1;
      Insets inserts = new Insets(top, left, bottom, right);

      c.fill = 1;
      c.weightx = 0.0D;
      c.weighty = 0.0D;
      c.insets = inserts;
      c.anchor = 21;
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 1;
      c.gridheight = 1;
      titleControlPanel.add(getTitleLabel(), c);

      c.gridx = -1;
      c.weightx = 1.0D;
      titleControlPanel.add(new JLabel(), c);
      c.weightx = 0.0D;
    }

    return titleControlPanel;
  }

  private JLabel getTitleLabel() {
    if (titleLabel == null) {
      titleLabel = new JLabel();

      titleLabel.setFont(new Font("ËÎÌו", 0, 12));
      setTitleLabelText("Card Title");
      titleLabel.setBackground(Color.RED);
    }

    return titleLabel;
  }

  public void setTitleLabelText(String title) {
    if ((titleLabel != null) && (title != null))
    {
      titleLabel.setText(title);
    }
  }

  public JPanel getPageContainer()
  {
    if (pageContainer == null) {
      pageContainer = new JPanel();
      if (card == null) {
        card = new CardLayout();
        pageContainer.setLayout(card);
      }
    }
    return pageContainer;
  }

  public void setDefaultPage(IPage cardComponet) {
    if ((cardComponet != null) && 
      ((cardComponet instanceof Component))) {
      this.defaultPage = cardComponet;
      this.defaultPage.setDefaultPage(true);
      addPage((Component)this.defaultPage, this.defaultPage.getPageId());
    }
  }

  public IPage getDefaultPage() {
    return this.defaultPage;
  }
  public void addPage(Component comp, Object constraints) {
    Component[] components = getPageContainer().getComponents();

    getPageContainer().add(comp, constraints);
    show((String)constraints);
  }

  public void show(String name) {
    card.show(pageContainer, name);
    updateTitleControlPanel();
  }

  private void updateTitleControlPanel()
  {
    if (this.defaultPage != null) {
      setTitleLabelText(this.defaultPage.getPageName());
    } else {
      setTitleLabelText("Chang to Defautl title.");

      return;
    }
  }

  public void ancestorAdded(AncestorEvent event)
  {
  }

  public void ancestorRemoved(AncestorEvent event)
  {
  }

  public void ancestorMoved(AncestorEvent event)
  {
  }
}