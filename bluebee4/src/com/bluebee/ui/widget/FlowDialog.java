package com.bluebee.ui.widget;

import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Flowlog;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.frame.FlowPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FlowDialog extends JDialog
{
  private JTextField textField_1;
  private JTextField txtDd;
  private JLabel label_4;
  private String dubString = "1234567890.";
  private String flownos;
  private String noss;
  private JTable table1s;
  private FlowPanel flowPanel;

  public FlowDialog(Component owner, JTable table1, String flowno, String no, String shoujia, String shulang)
  {
    setResizable(false);
    setSize(new Dimension(243, 199));
    setLocationRelativeTo(owner);
    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    this.noss = no;
    this.flownos = flowno;
    this.table1s = table1;
    this.flowPanel = ((FlowPanel)owner);

    setTitle("流水账修改");
    getContentPane().setLayout(null);

    JLabel label = new JLabel("货 号");
    label.setFont(new Font("宋体", 1, 18));
    label.setBounds(26, 27, 54, 21);
    getContentPane().add(label);

    JLabel label_1 = new JLabel("数 量");
    label_1.setFont(new Font("宋体", 1, 18));
    label_1.setBounds(26, 58, 54, 15);
    getContentPane().add(label_1);

    this.textField_1 = new JTextField();
    this.textField_1.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_1.setText(shulang);

    this.textField_1.setFont(new Font("宋体", 1, 18));
    this.textField_1.setBounds(90, 52, 106, 27);
    getContentPane().add(this.textField_1);
    this.textField_1.setColumns(10);

    JLabel label_2 = new JLabel("售 价");
    label_2.setFont(new Font("宋体", 1, 18));
    label_2.setBounds(26, 89, 54, 15);
    getContentPane().add(label_2);

    this.txtDd = new JTextField();
    this.txtDd.setDocument(new LimitedDocument(20, this.dubString));
    this.txtDd.setText(shoujia);

    this.txtDd.setFont(new Font("宋体", 1, 18));
    this.txtDd.setBounds(90, 83, 106, 27);
    getContentPane().add(this.txtDd);
    this.txtDd.setColumns(10);

    JButton button = new JButton("修 改");
    button.setFont(new Font("宋体", 1, 12));
    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        String sl = FlowDialog.this.textField_1.getText();
        if (sl.trim().length() == 0) {
          FlowDialog.this.label_4.setText("请输入数量");
          return;
        }
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        Stock stock = stockMoudle.getLastStockByNo(FlowDialog.this.noss);
        Flowlog flowlog = flowLogMoudle.getFlowByflowno(FlowDialog.this.flownos);

        if (stock == null) {
          FlowDialog.this.label_4.setText("输入货物号码不存在或者已经售完,请重新输入!");
          return;
        }
        double syamount = stock.getSyamount();
        if (flowlog.getAmount() > syamount) {
          FlowDialog.this.label_4.setText("输入数量大于库存剩余数量，库存目前剩余" + syamount);
          return;
        }

        double olbamount = flowlog.getAmount();
        double newamount = Double.parseDouble(String.valueOf(sl));
        double r = newamount - olbamount;
        String sj = FlowDialog.this.txtDd.getText();
        if (sj.trim().length() == 0) {
          FlowDialog.this.label_4.setText("请输入售价");
          return;
        }
        BigDecimal sell = new BigDecimal(sj);
        if (sell.compareTo(new BigDecimal("0")) == 0) {
          FlowDialog.this.label_4.setText("输入货物销售价格错误!");
          FlowDialog.this.txtDd.requestFocus();
          return;
        }
        if (r > 0.0D)
          stockMoudle.updateSyAmount(flowlog.getCatno(), r, "-");
        else if (r < 0.0D) {
          stockMoudle.updateSyAmount(flowlog.getCatno(), r * -1.0D, "+");
        }
        BigDecimal amount = new BigDecimal(String.valueOf(sl));
        BigDecimal lr = sell.subtract(flowlog.getCostprice()).multiply(amount);

        flowlog.setLrprice(lr);
        flowlog.setAmount(newamount);
        BigDecimal sellprice = new BigDecimal(String.valueOf(sj));
        flowlog.setSellprice(sellprice);
        flowlog.setLrprice(lr);
        if (r > 0.0D)
          flowLogMoudle.update(flowlog, "-");
        else if (r < 0.0D)
          flowLogMoudle.update(flowlog, "+");
        else {
          flowLogMoudle.update(flowlog, null);
        }
        int row = FlowDialog.this.table1s.getSelectedRow();
        if (row > -1) {
          Object[] rowData = { 
            flowlog.getFlowno(), 
            flowlog.getCatno(), 
            flowlog.getSellprice(), 
            flowlog.getLrprice(), 
            Double.valueOf(flowlog.getAmount()), 
            flowlog.getCostprice(), 
            flowlog.getType(), 
            flowlog.getDate(), 
            "删除" };

          DefaultTableModel defaultTableModel = (DefaultTableModel)FlowDialog.this.table1s.getModel();
          defaultTableModel.removeRow(row);
          defaultTableModel.insertRow(row, rowData);
          FlowDialog.this.flowPanel.deltail();
        }
        FlowDialog.this.dispose();
      }
    });
    button.setBounds(112, 120, 84, 27);
    getContentPane().add(button);
    JLabel label_3 = new JLabel("<html><u>" + no + "</u></html>");
    label_3.setFont(new Font("宋体", 1, 18));
    label_3.setBounds(90, 25, 106, 21);
    getContentPane().add(label_3);

    this.label_4 = new JLabel("");
    this.label_4.setForeground(Color.RED);
    this.label_4.setBounds(26, 10, 201, 15);
    getContentPane().add(this.label_4);
  }
}