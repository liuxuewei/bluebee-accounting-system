package com.bluebee.ui.widget;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class TCPopupEventQueue extends EventQueue
{
  public JPopupMenu popup;
  public BasicAction cut;
  public BasicAction copy;
  public BasicAction paste;
  public BasicAction selectAll;

  public void createPopupMenu(JTextComponent text)
  {
    this.cut = new CutAction("¼ôÇÐ", null);
    this.copy = new CopyAction("¸´ÖÆ", null);
    this.paste = new PasteAction("Õ³Ìù", null);
    this.selectAll = new SelectAllAction("È«Ñ¡", null);
    this.cut.setTextComponent(text);
    this.copy.setTextComponent(text);
    this.paste.setTextComponent(text);
    this.selectAll.setTextComponent(text);
    this.popup = new JPopupMenu();
    this.popup.add(this.cut);
    this.popup.add(this.copy);
    this.popup.add(this.paste);
    this.popup.addSeparator();
    this.popup.add(this.selectAll);
  }

  public void showPopup(Component parent, MouseEvent me) {
    this.popup.validate();
    this.popup.show(parent, me.getX(), me.getY());
  }

  protected void dispatchEvent(AWTEvent event) {
    super.dispatchEvent(event);
    if (!(event instanceof MouseEvent)) {
      return;
    }
    MouseEvent me = (MouseEvent)event;
    if (!me.isPopupTrigger()) {
      return;
    }
    if (!(me.getSource() instanceof Component)) {
      return;
    }
    Component comp = SwingUtilities.getDeepestComponentAt((Component)
      me.getSource(), me.getX(), me.getY());
    if (!(comp instanceof JTextComponent)) {
      return;
    }
    if (MenuSelectionManager.defaultManager().getSelectedPath().length > 0) {
      return;
    }
    createPopupMenu((JTextComponent)comp);
    showPopup((Component)me.getSource(), me);
  }

  public abstract class BasicAction extends AbstractAction {
    JTextComponent comp;

    public BasicAction(String text, Icon icon) {
      super(text,icon);
      putValue("ShortDescription", text);
    }

    public void setTextComponent(JTextComponent comp) {
      this.comp = comp;
    }

    public abstract void actionPerformed(ActionEvent paramActionEvent);
  }

  public class CopyAction extends TCPopupEventQueue.BasicAction
  {
    public CopyAction(String text, Icon icon)
    {
      super(text, icon);
      putValue("AcceleratorKey", KeyStroke.getKeyStroke("ctrl C"));
    }

    public void actionPerformed(ActionEvent e) {
      this.comp.copy();
    }

    public boolean isEnabled() {
      return (this.comp != null) && (this.comp.getSelectedText() != null);
    }
  }

  public class CutAction extends TCPopupEventQueue.BasicAction
  {
    public CutAction(String text, Icon icon)
    {
      super(text, icon);
      putValue("AcceleratorKey", KeyStroke.getKeyStroke("ctrl X"));
    }

    public void actionPerformed(ActionEvent e) {
      this.comp.cut();
    }

    public boolean isEnabled() {
      return (this.comp != null) && (this.comp.isEditable()) && (this.comp.getSelectedText() != null);
    }
  }

  public class PasteAction extends TCPopupEventQueue.BasicAction
  {
    public PasteAction(String text, Icon icon)
    {
      super(text, icon);
      putValue("AcceleratorKey", KeyStroke.getKeyStroke("ctrl V"));
    }

    public void actionPerformed(ActionEvent e) {
      this.comp.paste();
    }

    public boolean isEnabled() {
      Transferable content = Toolkit.getDefaultToolkit().getSystemClipboard()
        .getContents(null);

      return (this.comp != null) && (this.comp.isEnabled()) && (this.comp.isEditable()) && 
        (content.isDataFlavorSupported(DataFlavor.stringFlavor));
    }
  }

  public class SelectAllAction extends TCPopupEventQueue.BasicAction {
    public SelectAllAction(String text, Icon icon) {
      super(text, icon);
      putValue("AcceleratorKey", KeyStroke.getKeyStroke("ctrl A"));
    }

    public void actionPerformed(ActionEvent e) {
      this.comp.selectAll();
    }

    public boolean isEnabled()
    {
      return (this.comp != null) && (this.comp.isEnabled()) && (this.comp.getText().length() > 0) && (
        (this.comp.getSelectedText() == null) || 
        (this.comp.getSelectedText().length() < this.comp.getText().length()));
    }
  }
}