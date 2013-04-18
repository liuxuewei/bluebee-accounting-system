package com.bluebee.ui.frame;

import com.bluebee.Version;
import com.bluebee.util.Message;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UpdatePanel extends JPanel implements IPage {
	private static UpdatePanel updatePanel = new UpdatePanel();

	private boolean isDefaultPage = false;

	public static UpdatePanel getInstance() {
		if (updatePanel != null) {
			updatePanel = new UpdatePanel();
		}
		return updatePanel;
	}

	private UpdatePanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "升级软件", 4, 2, null, null));
		add(panel, "North");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 37, 284 };
		gbl_panel.rowHeights = new int[] { 36, 41, 19, 34 };
		gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
		gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
		panel.setLayout(gbl_panel);

		JLabel lblv = new JLabel("您的当前版本:V"
				+ Version.getInstance().getVersion());
		GridBagConstraints gbc_lblv = new GridBagConstraints();
		gbc_lblv.anchor = 17;
		gbc_lblv.insets = new Insets(0, 0, 5, 5);
		gbc_lblv.gridx = 1;
		gbc_lblv.gridy = 0;
		panel.add(lblv, gbc_lblv);

		JLabel label = new JLabel("你可以立即检查更新，升级到最新版本。查看更多信息请访问");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);

		JLabel label_1 = new JLabel("<html><u>http://code.google.com/p/bluebee-accounting-system/</u></html");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = 13;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		panel.add(label_1, gbc_label_1);

		JButton button = new JButton("在线升级 ");

		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				final String[] ok = { "确定" };
				String[] cancel = { "取消" };
				String mess = "正在获取BlueBee蓝蜜蜂记账软件版本信息，请稍候..........";
				Message.getInstance().setText(mess);
				final JOptionPane pane = new JOptionPane(mess);
				pane.setOptions(cancel);
				final JDialog dialog = pane.createDialog(
						UpdatePanel.updatePanel, "BlueBee蓝蜜蜂软件升级");
				Thread thread1 = new Thread(new Runnable() {
					public void run() {
						Message.getInstance().setText("正在连接网络 请稍候.........");
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								pane.setMessage(Message.getInstance().getText());
							}
						});
						String succ = null;
						URL urlStr = null;
						String versionurl = "http://bluebee-accounting-system.googlecode.com/files/version.txt";
						try {
							urlStr = new URL(versionurl);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						HttpURLConnection connection = null;
						try {
							connection = (HttpURLConnection) urlStr
									.openConnection();
						} catch (IOException e1) {
							e1.printStackTrace();
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									pane.setOptions(ok);
									pane.setMessage("无法连接升级服务器,原因可能网络不通");
								}
							});
							return;
						}
						int state = 0;
						try {
							state = connection.getResponseCode();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						boolean isupdate = false;

						if (state != 200) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									pane.setOptions(ok);
									pane.setMessage("无法连接升级服务器,原因可能网络不通");
								}
							});
							return;
						}
						if (state == 200) {
							succ = connection.getURL().toString();
							if (!succ.equals(versionurl)) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										pane.setOptions(ok);
										pane.setMessage("无法连接升级服务器,原因可能网络不通");
									}
								});
								return;
							}
							InputStream input = null;
							try {
								input = connection.getInputStream();
								byte[] data = new byte[1024];
								input.read(data, 0, 1024);
								float newversion = Float.parseFloat(new String(
										data));
								float cversion = Float.parseFloat(Version
										.getInstance().getVersion());
								isupdate = newversion <= cversion;
							} catch (IOException e1) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										pane.setOptions(ok);
										pane.setMessage("无法连接升级服务器,原因可能网络不通");
									}
								});
								return;
							} finally {
								if (input != null) {
									try {
										input.close();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								if (connection != null) {
									connection.disconnect();
								}

							}

							if (isupdate) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										pane.setOptions(ok);
										pane.setMessage("当前版本已是最新,无需更新");
									}
								});
							} else {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										pane.setMessage("正在下载升级程序中.........");
									}
								});
								URL main = null;
								try {
									main = new URL(
											"http://bluebee-accounting-system.googlecode.com/files/update.exe");
								} catch (MalformedURLException e1) {
									e1.printStackTrace();
								}
								HttpURLConnection mainconnection = null;
								int mainstate = 0;
								try {
									mainconnection = (HttpURLConnection) main
											.openConnection();
									mainstate = mainconnection
											.getResponseCode();
								} catch (IOException e1) {
									EventQueue.invokeLater(new Runnable() {
										public void run() {
											pane.setOptions(ok);
											pane.setMessage("无法连接升级服务器,原因可能网络不通");
										}
									});
									return;
								}
								if (mainstate == 200) {
									int nFileLength = -1;
									for (int i = 1;; i++) {
										String sHeader = mainconnection
												.getHeaderFieldKey(i);
										if (sHeader == null)
											break;
										if (sHeader.equals("Content-Length")) {
											nFileLength = Integer
													.parseInt(mainconnection
															.getHeaderField(sHeader));
											break;
										}
									}
									String sHeader;
									if (nFileLength > 0) {
										File oSavedFile = new File(
												"./update.exe");

										FileOutputStream fileOutputStream = null;
										try {
											fileOutputStream = new FileOutputStream(
													oSavedFile);
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										}

										int nStartPos = 0;
										int nRead = 0;
										InputStream mianinput = null;

										label846: try {
											mianinput = mainconnection
													.getInputStream();
											byte[] maindata = new byte[2024];
											do {
												if (!dialog.isVisible()) {
													EventQueue
															.invokeLater(new Runnable() {
																public void run() {
																	pane.setMessage("正在取消中........");
																}
															});
													if (!Thread.currentThread()
															.isAlive())
														break;
													Thread.currentThread()
															.interrupt();

													break label846;
												}

												fileOutputStream.write(
														maindata, 0, nRead);
												nStartPos += nRead;
												double z = nStartPos
														/ nFileLength;

												Message.getInstance()
														.setText(
																"正在下载升级程序,当前已完成"
																		+ UpdatePanel
																				.formatRates(z));
												EventQueue
														.invokeLater(new Runnable() {
															public void run() {
																pane.setMessage(Message
																		.getInstance()
																		.getText());
															}
														});
												if ((nRead = mianinput.read(
														maindata, 0, 2024)) <= 0)
													break;
											} while (nStartPos < nFileLength);
										} catch (IOException e) {
											e.printStackTrace();
											EventQueue
													.invokeLater(new Runnable() {
														public void run() {
															pane.setOptions(ok);
															pane.setMessage("下载升级程序出错,请重新下载");
														}
													});
										} finally {
											try {
												if (mianinput != null) {
													fileOutputStream.close();
													mianinput.close();
												}
											} catch (IOException e) {
												e.printStackTrace();
											}
											mainconnection.disconnect();
										}

										if (nStartPos != nFileLength) {
											boolean ide = oSavedFile.delete();

											EventQueue
													.invokeLater(new Runnable() {
														public void run() {
															pane.setOptions(ok);
															pane.setMessage("软件升级未完成");
														}
													});
										} else if (nStartPos == nFileLength) {
											Message.getInstance().setText(
													"软件升级成功完成！请重新启动BlueBee蓝蜜蜂记账系统");
											EventQueue
													.invokeLater(new Runnable() {
														public void run() {
															pane
																	.setOptions(ok);
															Message.getInstance()
																	.setCode(
																			"s");
															pane
																	.setMessage(Message
																			.getInstance()
																			.getText());
														}
													});
										}
									}
								}
							}
						}
					}
				}, "update");
				thread1.start();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						EventQueue theQueue = dialog.getToolkit()
								.getSystemEventQueue();
						while (dialog.isVisible())
							try {
								AWTEvent event = theQueue.getNextEvent();
								Object src = event.getSource();
								if ((event instanceof ActiveEvent))
									((ActiveEvent) event).dispatch();
								else if ((src instanceof Component))
									((Component) src).dispatchEvent(event);
								else if ((src instanceof MenuComponent)) {
									((MenuComponent) src).dispatchEvent(event);
								}
								if (!pane.getOptions()[0].equals("确定"))
									;
							} catch (Exception ex) {
							}
					}
				});
				dialog.setVisible(true);
				Object ovb = pane.getSelectionValues();

				if (!"s".equals(Message.getInstance().getCode())) {
					if (thread1.isAlive()) {
						thread1.interrupt();
					}
					File oSavedFile = new File("./update.exe");
					boolean isdeleted;
					if (oSavedFile.exists())
						isdeleted = oSavedFile.delete();
				}
			}
		});
		button.setHorizontalAlignment(4);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = 13;
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		panel.add(button, gbc_button);

		JPanel panel_1 = new JPanel();

		panel_1.setBorder(new TitledBorder(null, "升级说明", 4, 2, null, null));

		add(panel_1, "Center");

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 67 };

		gbl_panel_1.rowHeights = new int[] { 30, 26 };

		gbl_panel_1.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };

		gbl_panel_1.rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };

		panel_1.setLayout(gbl_panel_1);

		JLabel lbln = new JLabel("1.保证网络畅通");
		GridBagConstraints gbc_lbln = new GridBagConstraints();
		gbc_lbln.anchor = 17;
		gbc_lbln.insets = new Insets(0, 0, 5, 5);
		gbc_lbln.gridx = 1;
		gbc_lbln.gridy = 0;
		panel_1.add(lbln, gbc_lbln);

		JLabel label_2 = new JLabel("2.升级成功后，请先退出BlueBee蓝蜜蜂流水记账后，然后重新启动BlueBee蓝蜜蜂系统");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 1;
		panel_1.add(label_2, gbc_label_2);
	}

	public String getPageId() {
		return getClass().getName();
	}

	public String getPageName() {
		return ">>>软件升级";
	}

	public void disposePage() {
	}

	public boolean isDefaultPage() {
		return this.isDefaultPage;
	}

	public void setDefaultPage(boolean bool) {
		this.isDefaultPage = bool;
	}

	public static String formatRates(double val) {
		Double ret = null;
		val *= 100.0D;
		int precision = 2;
		try {
			double factor = Math.pow(10.0D, precision);
			ret = new Double(Math.floor(val * factor + 0.5D) / factor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String tmp = String.valueOf(ret);
		if (tmp.substring(tmp.indexOf('.') + 1).length() < 2) {
			tmp = tmp + "0";
		}
		return tmp + "%";
	}
}