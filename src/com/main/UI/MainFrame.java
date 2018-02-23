package com.main.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.algorithm.closestPair.EfficientClosestPair;
import com.algorithm.closestPair.EfficientClosestPair.DimensionWithPoints;
import com.algorithm.trisectionSearch.TrisectionSearch;
import com.common.UI.MyButton;
import com.common.UI.MyFrame;
import com.common.UI.MyPanel;
import com.common.UI.theme.ButtonTheme;
import com.common.UI.theme.FrameTheme;
import com.main.UI.theme.DefaultPanelTheme;
import com.mine.IO.XmlHelper;

public class MainFrame {
	private MyFrame frame;
	private MyFrame controlor;
	private int defaultX;
	private int defaultY;
	private MyFileDialog loadDialog, saveDialog;

	MapPanelTheme theme;

	Type type = Type.sort;

	private enum Type {
		sort, pointPair
	}

	private class itermButtonTheme extends ButtonTheme {
		String s;
		int x;
		int y;
		Font font;

		public itermButtonTheme(String s, int x, int y) {
			this.s = s;
			this.x = x;
			this.y = y;
			font = new Font("楷体", Font.BOLD, 18);
		}

		/**
		 * 给mybutton设定背景图片
		 * 
		 * @param g
		 *            mybutton的画笔
		 */
		public void setBackgroudImage(Graphics g) {
			Graphics g0 = g.create();
			Color c0 = g0.getColor();
			Color c1 = new Color(0x141947);
			g0.setColor(c);
			switch (status) {
			case release:
				g0.setColor(new Color(0xb7d8fa));
				break;
			case press:
				g0.setColor(new Color(0x6981aa));
				break;
			case hover:
				g0.setColor(new Color(0x8bb7e5));
				break;
			default:
				break;
			}
			g0.fillRect(0, 0, mybutton.getWidth(), mybutton.getHeight());
			g0.setFont(font);
			g0.setColor(c1);
			g0.drawString(s, x, y);
			g0.setColor(c0);
		}
	}

	boolean isSort = false;

	public MainFrame() {
		frame = new MyFrame(new FrameTheme("img/backgroud.png") {
		});
		controlor = new MyFrame(new FrameTheme("img/controlor.png") {
		});
		MyButton close = new MyButton(
				new ButtonTheme("img/close_button.png", "img/close_button_press.png", "img/close_button_hover.png") {
					@Override
					public void onClick(MouseEvent e) {
						super.onClick(e);
						System.exit(0);
					}
				});

		frame.setLocationRelativeTo(null);

		controlor.add(close);
		close.setLocation(138, 22);
		close.setSize(20, 20);

		MyPanel map = new MyPanel(new MapPanelTheme(Color.WHITE));
		map.setSize(590, 360);
		map.setLocation(120, 110);
		frame.add(map);
		theme = (MapPanelTheme) map.getTheme();

		MyButton refresh = new MyButton(new itermButtonTheme("生成数据", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				((MapPanelTheme) (map.getTheme())).reset();
				frame.setLocation(defaultX, defaultY);
				initNumber();
				isSort = false;
				sorted = false;
				theme.ifNeedRepaintSort(true);
				theme.repaint();
			}
		});
		refresh.setSize(110, 30);

		refresh.setLocation(32, 100);

		MyButton relocate = new MyButton(new itermButtonTheme("快速排序", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				if (data == null) {
					JOptionPane.showConfirmDialog(null, "请先生成数据", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				if (!isSort) {
					isSort = true;
					if (!sorted)
						new Thread() {
							public void run() {
								quickSort(data, 0, data.length - 1);
								sorted = true;
								theme.ifNeedRepaintSort(true);
								theme.repaint();
								isSort = false;
							};
						}.start();
				} else
					JOptionPane.showConfirmDialog(null, "已经在排序", "错误", JOptionPane.YES_NO_OPTION);

			}
		});
		relocate.setSize(110, 30);

		relocate.setLocation(32, 150);

		MyButton open = new MyButton(new itermButtonTheme("归并排序", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				if (data == null) {
					JOptionPane.showConfirmDialog(null, "请先生成数据", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				if (!isSort) {
					isSort = true;
					if (!sorted)
						new Thread() {
							public void run() {
								mergeSort(data, 0, data.length - 1);
								sorted = true;
								theme.ifNeedRepaintSort(true);
								theme.repaint();
								isSort = false;
							};
						}.start();
				} else
					JOptionPane.showConfirmDialog(null, "已经在排序", "错误", JOptionPane.YES_NO_OPTION);

			}
		});
		open.setSize(110, 30);

		open.setLocation(32, 200);

		MyButton binarySearch = new MyButton(new itermButtonTheme("二分检索", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				if (data == null) {
					JOptionPane.showConfirmDialog(null, "请先生成数据", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				if (!sorted) {
					JOptionPane.showConfirmDialog(null, "请先排序", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				String s = JOptionPane.showInputDialog("请输入需要检索的数字");
				if (s != null)
					try {
						double d = new Double(s);
						int index = TrisectionSearch.trisecionSearch(data, d);
						// int index = binarySearch(data, 0, data.length - 1,
						// d);
						if (index != -1) {
							theme.find(index);
						} else
							JOptionPane.showConfirmDialog(null, "输入数字没有找到", "错误", JOptionPane.YES_NO_OPTION);
					} catch (NumberFormatException e1) {
						JOptionPane.showConfirmDialog(null, "输入数字不符合规定", "错误", JOptionPane.YES_NO_OPTION);
					}
			}
		});
		binarySearch.setSize(110, 30);

		binarySearch.setLocation(32, 250);

		MyButton triSearch = new MyButton(new itermButtonTheme("三分检索", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				if (data == null) {
					JOptionPane.showConfirmDialog(null, "请先生成数据", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				if (!sorted) {
					JOptionPane.showConfirmDialog(null, "请先排序", "错误", JOptionPane.YES_NO_OPTION);
					return;
				}
				String s = JOptionPane.showInputDialog("请输入需要检索的数字");
				if (s != null)
					try {
						double d = new Double(s);
						int index = binarySearch(data, 0, data.length - 1, d);
						if (index != -1) {
							theme.find(index);
						} else
							JOptionPane.showConfirmDialog(null, "输入数字没有找到", "错误", JOptionPane.YES_NO_OPTION);
					} catch (NumberFormatException e1) {
						JOptionPane.showConfirmDialog(null, "输入数字不符合规定", "错误", JOptionPane.YES_NO_OPTION);
					}
			}
		});
		triSearch.setSize(110, 30);

		triSearch.setLocation(32, 300);

		MyButton createPoints = new MyButton(new itermButtonTheme("生成点群", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				initPoints();
				theme.ifNeedRepaintPoint(true);
				theme.repaint();
				map.repaint();
			}
		});
		createPoints.setSize(110, 30);

		createPoints.setLocation(32, 100);

		MyButton findPairs = new MyButton(new itermButtonTheme("寻找点对", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);

				new Thread() {
					public void run() {
						EfficientClosestPair closest = new EfficientClosestPair();
						DimensionWithPoints d = closest.closestPair(points, sizeOfPoint);
						theme.find(d);
					};
				}.start();

			}
		});
		findPairs.setSize(110, 30);

		findPairs.setLocation(32, 150);

		MyButton createGraphics = new MyButton(new itermButtonTheme("生成  图", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);

				initPoints();
				initLinkPointsPoints();
				theme.ifNeedRepaintPoint(true);
				theme.repaint();
				map.repaint();
			}
		});
		createGraphics.setSize(110, 30);

		createGraphics.setLocation(32, 200);

		MyButton dfs = new MyButton(new itermButtonTheme("深度优先", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);

				int number = dfs(linkPoints);
				System.out.println(number);
				theme.ifNeedRepaintPoint(true);
				theme.repaint();
				System.out.println(number);
				JOptionPane.showConfirmDialog(null, "连通分量数是：" + number, "结果", JOptionPane.YES_NO_OPTION);
				map.repaint();
			}
		});
		dfs.setSize(110, 30);

		dfs.setLocation(32, 250);

		controlor.add(refresh);
		controlor.add(relocate);
		controlor.add(open);
		controlor.add(binarySearch);
		controlor.add(triSearch);

		final MyButton saveData = new MyButton(new itermButtonTheme("保存数据", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				if (isSort) {
					JOptionPane.showConfirmDialog(null, "正在排序，此时不能保存数据", "错误", JOptionPane.YES_NO_OPTION);
				}
				File file = openSaveWindow();
				if (file == null)
					return;
				try {
					Document document = XmlHelper.getDefaultXML();
					Element root = document.createElement("myData");
					if (data != null) {
						Element number = document.createElement("number");
						root.appendChild(number);
						String content = "\n";
						for (int i = 0; i < data.length; i++) {
							content += data[i] + "\n";
						}
						number.setTextContent(content);
					}
					if (points != null) {
						Element ps = document.createElement("points");
						root.appendChild(ps);
						EfficientClosestPair closest = new EfficientClosestPair();
						DimensionWithPoints d = closest.closestPair(points, sizeOfPoint);
						ps.setAttribute("最近距离", d.getDimension() + "");
						Point[] closetPoints = d.getPoints();
						ps.setAttribute("p1.x", closetPoints[0].x + "");
						ps.setAttribute("p1.y", closetPoints[0].y + "");
						ps.setAttribute("p2.x", closetPoints[1].x + "");
						ps.setAttribute("p2.y", closetPoints[1].y + "");

						for (int i = 0; i < points.length; i++) {
							Element point = document.createElement("point");
							point.setAttribute("x", points[i].x + "");
							point.setAttribute("y", points[i].y + "");
							ps.appendChild(point);
						}
					}
					document.appendChild(root);
					XmlHelper.saveXML(document, file);

				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					e1.printStackTrace();
				}
			}
		});
		saveData.setSize(110, 30);
		controlor.add(saveData);
		saveData.setLocation(32, 400);

		MyButton typeChange = new MyButton(new itermButtonTheme("排序算法", 16, 22) {
			@Override
			public void onClick(MouseEvent e) {
				super.onClick(e);
				if (type == Type.sort) {
					type = Type.pointPair;
					s = "最近点对";
					// controlor.removeAll();
					controlor.remove(refresh);
					controlor.remove(relocate);
					controlor.remove(open);
					controlor.remove(binarySearch);
					controlor.remove(triSearch);
					// System.out.println(close);
					// controlor.add(close);
					controlor.add(createPoints);
					controlor.add(findPairs);
					controlor.add(createGraphics);
					controlor.add(dfs);
					controlor.add(mybutton);
					// controlor.add(detail);
					controlor.repaint();

				} else if (type == Type.pointPair) {
					type = Type.sort;
					s = "排序算法";
					// controlor.removeAll();
					// controlor.add(close);
					controlor.remove(createPoints);
					controlor.remove(findPairs);
					controlor.remove(createGraphics);
					controlor.remove(dfs);
					controlor.add(refresh);
					controlor.add(relocate);
					controlor.add(open);
					controlor.add(binarySearch);
					controlor.add(mybutton);
					controlor.add(triSearch);
					// controlor.add(detail);
					controlor.repaint();
				}
				theme.updateType();
			}
		});
		typeChange.setSize(110, 30);
		controlor.add(typeChange);
		typeChange.setLocation(32, 50);

		controlor.setLocationRelativeTo(frame);
		controlor.setLocation(frame.getX() + frame.getTheme().getBackgroudImage().getWidth(null), frame.getY() + 52);
	}

	int r = 20;

	class MapPanelTheme extends DefaultPanelTheme implements MouseListener, MouseMotionListener, MouseWheelListener {

		int x0, y0, x, y, dx = 0, dy = 0;
		boolean ifDragged = false;

		BufferedImage backgroundImage, sort, point;
		boolean need_repaint_sort = false;
		boolean need_repaint_point = false;

		public MapPanelTheme(Color color) {
			super(color);

		}

		@Override
		public void init(Container container) {
			super.init(container);
		}

		public void find(DimensionWithPoints d) {
			int width = point.getWidth();
			int height = point.getHeight();
			Graphics g = point.getGraphics();
			g.setColor(Color.BLUE);

			Point[] points = d.getPoints();
			for (Point p : points) {
				g.fillOval(p.x - r, p.y - r, r, r);
			}
			g.setColor(new Color(0x141947));
			g.setFont(font);
			g.drawString("最近距离是：" + d.getDimension(), 2, 12);

			mypanel.getGraphics().drawImage(backgroundImage, dx, dy, (int) (width / this.scale),
					(int) (height / this.scale), null);

		}

		public void find(int index) {
			int width = backgroundImage.getWidth();
			int height = backgroundImage.getHeight();
			Graphics g = sort.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.RED);

			int wid = width / (data.length * 2 - 1);
			double scale = (max / height);
			int x = (width - (data.length * 2 - 1) * wid) / 2;
			for (int i = 0; i < data.length; i++) {
				int h = (int) (data[i] / scale);
				if (i == index) {
					g.setColor(Color.BLUE);
					g.fillRect(x, height - h, wid, h);
					g.setColor(Color.RED);
				} else
					g.fillRect(x, height - h, wid, h);
				x += wid * 2;
			}
			mypanel.getGraphics().drawImage(backgroundImage, dx, dy, (int) (width / this.scale),
					(int) (height / this.scale), null);
		}

		int rOfPoints = 3;

		public void ifNeedRepaintSort(boolean b) {
			need_repaint_sort = b;
		}

		public void ifNeedRepaintPoint(boolean b) {
			need_repaint_point = b;
		}

		public void repaint() {

			int width = backgroundImage.getWidth();
			int height = backgroundImage.getHeight();
			if (need_repaint_sort) {
				need_repaint_sort = false;
				if (data == null)
					return;
				Graphics g = sort.getGraphics();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, width, height);
				g.setColor(Color.RED);

				int wid = width / (data.length * 2 - 1);
				double scale = (max / height);
				int x = (width - (data.length * 2 - 1) * wid) / 2;
				for (int i = 0; i < data.length; i++) {
					int h = (int) (data[i] / scale);
					g.fillRect(x, height - h, wid, h);
					x += wid * 2;
				}
			} else if (need_repaint_point) {
				need_repaint_point = false;
				if (points == null)
					return;
				Graphics g = point.getGraphics();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, width, height);
				// g.setColor(Color.RED);

				Point p;
				for (int i = 0; i < sizeOfPoint; i++) {
					p = points[i];
					g.setColor(pointsColor[i]);
					g.fillOval(p.x - r, p.y - r, r, r);
				}
				g.setColor(new Color(0x20b0fb));
				Point p1;
				if (linkPoints != null)
					for (int i = 0; i < sizeOfPoint; i++) {
						p = points[i];
						if (linkPoints[i] != null)
							for (int j = 0; j < linkPoints[i].size(); j++) {
								p1 = points[j];
								g.drawLine(p.x - r / 2, p.y - r / 2, p1.x - r / 2, p1.y - r / 2);
							}

					}
			}
			mypanel.getGraphics().drawImage(backgroundImage, dx, dy, (int) (width / this.scale),
					(int) (height / this.scale), null);
		}

		public void updateType() {
			switch (type) {
			case pointPair:
				backgroundImage = point;
				break;
			case sort:
				backgroundImage = sort;
				break;
			default:
				break;

			}
			mypanel.repaint();
		}

		@Override
		public void setSize(int width, int height) {
			super.setSize(width, height);
			sort = new BufferedImage(mypanel.getWidth(), mypanel.getHeight(), 6);
			point = new BufferedImage(mypanel.getWidth(), mypanel.getHeight(), 6);
			backgroundImage = sort;
			Graphics g = backgroundImage.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null));

			g = point.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null));

		}

		public void reset() {
			scale = 1;
		}

		double scale = 1;

		@Override
		public void mouseDragged(MouseEvent e) {
			x0 = x;
			y0 = y;
			x = e.getX();
			y = e.getY();
			int tmpx = dx, tmpy = dy;
			tmpx += x - x0;
			if (tmpx <= 0 && (-tmpx) <= backgroundImage.getWidth(null) / scale - mypanel.getWidth())
				dx = tmpx;
			tmpy += y - y0;
			if (tmpy <= 0 && (-tmpy) <= backgroundImage.getHeight(null) / scale - mypanel.getHeight())
				dy = tmpy;
			mypanel.repaint();
		}

		Font font = new Font("楷体", Font.BOLD, 15);

		@Override
		public void mouseMoved(MouseEvent e) {
			if (backgroundImage == null)
				return;
			int width = backgroundImage.getWidth();
			int height = backgroundImage.getHeight();

			switch (type) {
			case pointPair:
				if (points == null)
					return;
				for (int i = 0; i < sizeOfPoint; i++) {
					if (Math.pow((e.getX() - points[i].x), 2) + Math.pow((e.getY() - points[i].y), 2) <= r * r) {
						Graphics g = mypanel.getGraphics();
						g.drawImage(backgroundImage, dx, dy, (int) (width / this.scale), (int) (height / this.scale),
								null);
						g.setColor(new Color(0x141947));
						g.setFont(font);
						g.drawString("(" + points[i].x + "," + points[i].y + ")", 2, height - 12);
					}
				}
				break;
			case sort:
				if (data == null)
					return;
				int wid = width / (data.length * 2 - 1);
				double scale = (max / height);
				int x0 = (width - (data.length * 2 - 1) * wid) / 2;
				int x = e.getX();
				int y = e.getY();
				if (x < x0)
					return;
				int turn = (x - x0) / wid;
				if (turn % 2 == 1)
					return;
				int index = turn / 2;
				if (index >= data.length)
					return;
				int h = (int) (data[index] / scale);
				if (y > height - h) {
					Graphics g = mypanel.getGraphics();
					g.drawImage(backgroundImage, dx, dy, (int) (width / this.scale), (int) (height / this.scale), null);
					g.setColor(new Color(0x141947));
					g.setFont(font);
					g.drawString(data[index] + "", 2, 12);
				}
				break;
			default:
				break;

			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (type) {
			case pointPair:
				addPoint(e.getX() + r / 2, e.getY() + r / 2);
				theme.ifNeedRepaintPoint(true);
				break;
			case sort:
				break;
			default:
				break;
			}
			repaint();
		}

		double tmp(double x) {
			return (x) / scale;
		}

		double cal(double x, double y, double x1, double y1) {
			return Math.sqrt(Math.pow(Math.abs(x - x1), 2) + Math.pow(Math.abs(y - y1), 2));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		public int getWidth() {
			if (backgroundImage != null)
				return backgroundImage.getWidth();
			return -1;
		}

		public int getHeight() {
			if (backgroundImage != null) {
				return backgroundImage.getHeight();
			}
			return -1;
		}

		/**
		 * 给窗体设置背景图片
		 * 
		 * @param g
		 *            窗体的画笔
		 */
		public void setBackgroudImage(Graphics g) {
			Graphics g0 = g.create();
			if (color != null) {
				g0.setColor(color);
				g0.fillRect(0, 0, mypanel.getWidth(), mypanel.getHeight());
			}
			if (backgroundImage != null) {
				g0.drawImage(backgroundImage, dx, dy, (int) (backgroundImage.getWidth(null) / scale),
						(int) (backgroundImage.getHeight(null) / scale), null);
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int direction = e.getWheelRotation();
			double amount = e.getScrollAmount();
			if (direction > 0) {
				double tmpScale = scale * (amount);
				if (backgroundImage.getWidth(null) / tmpScale >= mypanel.getWidth()
						&& backgroundImage.getHeight(null) / tmpScale >= mypanel.getHeight()) {
					scale = tmpScale;
					if (dx + (backgroundImage.getWidth(null) / scale) < mypanel.getWidth())
						dx = (int) (mypanel.getWidth() - backgroundImage.getWidth(null) / scale);

					if (dy + (backgroundImage.getHeight(null) / scale) < mypanel.getHeight())
						dy = (int) (mypanel.getHeight() - backgroundImage.getHeight(null) / scale);
				}
			} else if (direction < 0) {
				scale /= amount;

			}
			mypanel.repaint();
		}

	}

	public void setVisible(boolean visible) {
		controlor.setVisible(visible);
		frame.setVisible(visible);
		defaultX = frame.getX();
		defaultY = frame.getY();
	}

	/**
	 * 创建打开文件的对话框
	 */
	public File openLoadWindow() {
		loadDialog = new MyFileDialog(frame, "打开文件", FileDialog.LOAD);
		loadDialog.setLocationRelativeTo(null);
		loadDialog.setVisible(true);

		String directory, f;
		directory = loadDialog.getDirectory();
		f = loadDialog.getFile();
		if (directory != null && f != null) {
			return new File(directory + f);
		}
		return null;
	}

	/**
	 * 创建保存文件的对话框
	 */
	public File openSaveWindow() {
		saveDialog = new MyFileDialog(frame, "保存文件", FileDialog.SAVE);
		saveDialog.setLocationRelativeTo(null);
		saveDialog.setVisible(true);

		String directory, f;
		directory = saveDialog.getDirectory();
		f = saveDialog.getFile();

		if (directory != null && f != null) {
			return new File(directory + f);
		}
		return null;
	}

	volatile double[] data;
	double max;
	boolean sorted = false;

	public void initNumber() {
		int range = (int) (Math.random() * 20 + 20);
		data = new double[range];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random() * 50);
			if (max < data[i])
				max = data[i];
		}
		sorted = false;
		theme.repaint();
	}

	void mergeSort(double[] array, int low, int high) {
		try {
			if (low >= high)
				return;
			int mid = (low + high) / 2;
			mergeSort(array, low, mid);
			mergeSort(array, mid + 1, high);
			double[] temp1 = new double[mid - low + 1];
			double[] temp2 = new double[high - mid];
			System.arraycopy(array, low, temp1, 0, temp1.length);
			System.arraycopy(array, mid + 1, temp2, 0, temp2.length);
			int i1 = 0, i2 = 0;
			while (i1 < temp1.length && i2 < temp2.length) {
				if (temp1[i1] <= temp2[i2])
					array[low++] = temp1[i1++];
				else
					array[low++] = temp2[i2++];
				theme.ifNeedRepaintSort(true);
				theme.repaint();
				Thread.sleep(200);
			}
			while (i1 < temp1.length) {
				array[low++] = temp1[i1++];
				theme.ifNeedRepaintSort(true);
				theme.repaint();
				Thread.sleep(200);
			}
			while (i2 < temp2.length) {
				array[low++] = temp2[i2++];
				theme.ifNeedRepaintSort(true);
				theme.repaint();
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void quickSort(double[] array, int low, int high) {
		if (low < high) {
			int middle = getMiddle(array, low, high);
			quickSort(array, low, middle - 1);
			quickSort(array, middle + 1, high);
		}
	}

	int binarySearch(double[] array, int low, int high, double tar) {
		if (high < low)
			return -1;
		int mid = (low + high) / 2;
		if (array[mid] == tar) {
			return mid;
		} else if (array[mid] < tar) {
			return binarySearch(array, mid + 1, high, tar);
		} else {
			return binarySearch(array, low, mid - 1, tar);
		}
	}

	int getMiddle(double[] list, int low, int high) {
		double tmp = list[low];
		try {
			while (low < high) {
				while (low < high && list[high] >= tmp) {
					high--;
				}

				if (list[low] != list[high]) {
					list[low] = list[high];
					theme.ifNeedRepaintSort(true);
					theme.repaint();
					Thread.sleep(200);
				}

				while (low < high && list[low] <= tmp) {
					low++;
				}

				if (list[low] != list[high]) {
					list[high] = list[low];
					theme.ifNeedRepaintSort(true);
					theme.repaint();
					Thread.sleep(200);
				}
			}
			if (list[low] != tmp) {
				list[low] = tmp;
				theme.ifNeedRepaintSort(true);
				theme.repaint();
				Thread.sleep(200);
			}
			return low;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return -1;
	}

	double[] getData() {
		double[] d = new double[data.length];
		System.arraycopy(data, 0, d, 0, data.length);
		return d;
	}

	Point[] points;
	ArrayList<Integer>[] linkPoints;
	Color[] pointsColor;
	int sizeOfPoint;

	@SuppressWarnings("unchecked")
	void initPoints() {
		int length = (int) (Math.random() * 20 + 7);
		points = new Point[length];
		linkPoints = new ArrayList[length];
		pointsColor = new Color[length];
		for (int i = 0; i < pointsColor.length; i++) {
			pointsColor[i] = Color.BLUE;
		}
		sizeOfPoint = length - (int) (Math.random() * 5);
		int width = theme.getWidth();
		int height = theme.getHeight();
		for (int i = 0; i < sizeOfPoint; i++) {
			points[i] = new Point((int) (Math.random() * (width - r * 2) + r),
					(int) (Math.random() * (height - r * 2) + r));
		}
	}

	void initLinkPointsPoints() {
		for (int i = 0; i < sizeOfPoint; i++) {
			linkPoints[i] = new ArrayList<>(sizeOfPoint);
		}

		int[][] neighbour = new int[sizeOfPoint][sizeOfPoint];
		for (int i = 0; i < neighbour.length; i++) {
			for (int j = 0; j < i; j++) {
				neighbour[i][j] = neighbour[j][i] = (Math.random() >= 0.5) ? 1 : 0;
			}
			neighbour[i][i] = 0;
		}

		for (int i = 0; i < neighbour.length; i++) {
			for (int j = 0; j < neighbour[i].length; j++) {
				if (neighbour[i][j] == 1)
					linkPoints[i].add(j);
			}
		}
	}

	int dfs(ArrayList<Integer>[] linkPoints) {
		int[] findTable = new int[sizeOfPoint];
		int count = 0;
		for (int i = 0; i < findTable.length; i++) {
			if (findTable[i] == 0) {
				dfs(i, linkPoints, findTable);
				count++;
			}
		}
		return count;
	}

	// TODO
	void dfs(int start, ArrayList<Integer>[] linkPoints, int[] findTable) {
		pointsColor[start] = new Color(0x0e09e8);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theme.ifNeedRepaintPoint(true);
		theme.repaint();
		findTable[start] = 1;
		for (int i : linkPoints[start]) {
			if (findTable[i] == 0) {
				dfs(i, linkPoints, findTable);
			}
		}
		pointsColor[start] = new Color(0x060455);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theme.ifNeedRepaintPoint(true);
		theme.repaint();
	}

	void addPoint(int x, int y) {
		if (points == null) {
			points = new Point[10];
			sizeOfPoint = 0;
		}
		if (sizeOfPoint < points.length) {
			points[sizeOfPoint++] = new Point(x, y);
		} else {
			Point[] npoints = new Point[sizeOfPoint * 3 / 2];
			System.arraycopy(points, 0, npoints, 0, sizeOfPoint);
			npoints[sizeOfPoint++] = new Point(x, y);
			points = npoints;
		}
	}

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		m.setVisible(true);
	}
}
