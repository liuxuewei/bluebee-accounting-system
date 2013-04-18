package com.bluebee.ui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class SuggestTextField extends JTextField
{
  private JPopupMenu popupMenu;
  private JList cacheKeywordList;
  private Set<String> matchResultSet = new HashSet();
  private ResultListModel resultListModel;
  private ActionListener listListener;
  private SuggestData suggestData;
  private MUIManager uiManager;
  private Dimension dimension;
  private MUIKeyEvent mUIKeyEvent;

  public SuggestTextField(SuggestData suggestData, MUIManager uiManagerss, Dimension dimensionx, MUIKeyEvent mUIKeyEvents)
  {
    this.suggestData = suggestData;
    this.uiManager = uiManagerss;
    this.dimension = dimensionx;
    this.mUIKeyEvent = mUIKeyEvents;
    Listener listener = new Listener();
    KeyDisposer keyListener = new KeyDisposer();
    registerKeyboardAction(listener, KeyStroke.getKeyStroke(10, 0, false), 0);
    this.resultListModel = new ResultListModel();
    this.cacheKeywordList = new JList(this.resultListModel);
    this.cacheKeywordList.setBackground(Color.WHITE);
    this.cacheKeywordList.setSelectionMode(0);
    this.listListener = new ListListener();
    this.cacheKeywordList.registerKeyboardAction(this.listListener, KeyStroke.getKeyStroke(10, 0, false), 0);
    this.cacheKeywordList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        if (SuggestTextField.this.uiManager != null) {
          String[] arg = SuggestTextField.this.cacheKeywordList.getSelectedValue().toString().split(",");
          SuggestTextField.this.setText(arg[0]);
          SuggestTextField.this.popupMenu.setVisible(false);
          SuggestTextField.this.uiManager.updateView(arg);
        } else {
          SuggestTextField.this.setText(SuggestTextField.this.cacheKeywordList.getSelectedValue().toString());
          SuggestTextField.this.popupMenu.setVisible(false);
        }
        SuggestTextField.this.requestFocus();
      }
    });
    this.popupMenu = new JPopupMenu();

    this.popupMenu.setBorder(new LineBorder(null, 0));

    addKeyListener(keyListener);
    setVisible(true);
  }

  public void showMatch()
  {
    String keyWord = getText().trim();

    if ("".equals(keyWord)) {
      this.popupMenu.removeAll();
      this.popupMenu.setVisible(false);
      requestFocus();
      if (this.uiManager != null) {
        this.uiManager.updateView(null);
      }

      return;
    }
    Set<String> matchResult = getMatch(keyWord);
    if ((matchResult == null) || (matchResult.size() <= 0)) {
      this.popupMenu.setVisible(false);
      return;
    }
    this.resultListModel.removeAllElements();
    for (String result : matchResult)
      this.resultListModel.addElement(result);
    this.popupMenu.show(this, 0, getHeight());
    this.cacheKeywordList.setSelectedIndex(0);
    requestFocus();
  }

  private Set<String> getMatch(String content) {
    List<String> list = this.suggestData.getData(content);
    Set tempSet = new HashSet();
    if (list.size() > 0) {
      for (String temp : list) {
        if (temp.indexOf(content) != -1)
          tempSet.add(temp);
      }
      JComponent tipFrame = new JScrollPane(this.cacheKeywordList);
      tipFrame.setBackground(Color.WHITE);
      if (this.dimension != null) {
        this.dimension.setSize(this.dimension.getWidth(), tempSet.size() * 21);
        tipFrame.setPreferredSize(this.dimension);
      } else {
        tipFrame.setPreferredSize(new Dimension(super.getWidth() + 20, tempSet.size() * 21));
      }

      int c = this.popupMenu.getComponentCount();
      if (c == 0) {
        this.popupMenu.add(tipFrame);
      } else {
        this.popupMenu.setVisible(false);
        this.popupMenu.remove(0);
        this.popupMenu.add(tipFrame);
        this.popupMenu.setVisible(true);
      }
    } else {
      this.popupMenu.setVisible(false);
      this.popupMenu.removeAll();
    }
    return tempSet;
  }
  private class KeyDisposer extends KeyAdapter {
    private KeyDisposer() {
    }
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == 27) {
        SuggestTextField.this.popupMenu.setVisible(false);
      } else if (e.getKeyCode() == 40) {
        SuggestTextField.this.popupMenu.setVisible(true);
        SuggestTextField.this.cacheKeywordList.requestFocus();
        if (SuggestTextField.this.resultListModel.size() >= 2)
          SuggestTextField.this.cacheKeywordList.setSelectedIndex(1);
      } else if (e.getKeyCode() == 38) {
        SuggestTextField.this.popupMenu.setVisible(true);
        SuggestTextField.this.cacheKeywordList.requestFocus();
        SuggestTextField.this.cacheKeywordList.setSelectedIndex(SuggestTextField.this.resultListModel.size() - 1);
      } else if (e.getKeyCode() == 10) {
        if (SuggestTextField.this.mUIKeyEvent != null)
          SuggestTextField.this.mUIKeyEvent.vkEnterENTER();
      }
      else {
        SuggestTextField.this.showMatch();
      }
    }
  }

  private class ListListener
    implements ActionListener
  {
    private ListListener()
    {
    }

    public void actionPerformed(ActionEvent e)
    {
      if (SuggestTextField.this.uiManager != null) {
        String[] arg = SuggestTextField.this.cacheKeywordList.getSelectedValue().toString().split(",");
        SuggestTextField.this.setText(arg[0]);
        SuggestTextField.this.popupMenu.setVisible(false);
        SuggestTextField.this.uiManager.updateView(arg);
      } else {
        SuggestTextField.this.setText(SuggestTextField.this.cacheKeywordList.getSelectedValue().toString());
        SuggestTextField.this.popupMenu.setVisible(false);
      }
      SuggestTextField.this.requestFocus();
    }
  }

  class Listener extends MouseAdapter
    implements ActionListener
  {
    Listener()
    {
    }

    public void actionPerformed(ActionEvent e)
    {
      if (SuggestTextField.this.popupMenu.isShowing())
        SuggestTextField.this.listListener.actionPerformed(e);
    }
  }

  public static abstract interface MUIKeyEvent
  {
    public abstract void vkEnterENTER();
  }

  public static abstract interface MUIManager
  {
    public abstract void updateView(String[] paramArrayOfString);
  }

  private class ResultListModel extends DefaultListModel
  {
    ResultListModel()
    {
      String matchKeyword;
      for (Iterator localIterator = SuggestTextField.this.matchResultSet.iterator(); localIterator.hasNext(); ) { matchKeyword = (String)localIterator.next();
        addElement(matchKeyword);
      }
    }
  }

  public static abstract interface SuggestData
  {
    public abstract List<String> getData(String paramString);
  }
}