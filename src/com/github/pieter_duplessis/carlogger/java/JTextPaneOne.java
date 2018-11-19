package com.github.pieter_duplessis.carlogger.java;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;

public class JTextPaneOne extends JTextPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTextPaneOne() {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem selectAll = new JMenuItem("Select All");
		JMenuItem cutSelection = new JMenuItem("Cut Selection");
		JMenuItem copySelection = new JMenuItem("Copy Selection");
		JMenuItem pasteClipboard = new JMenuItem("Paste");
		popupMenu.add(selectAll);
		popupMenu.addSeparator();
		popupMenu.add(cutSelection);
		popupMenu.add(copySelection);
		popupMenu.add(pasteClipboard);
		addMouseListener(new MouseAdapter() {
			//Mouse Right-Click Pop-up
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					doPop(e);
				} else {
					hidePop();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					doPop(e);
				}
			}
			
			private void doPop(MouseEvent e) {
				requestFocus();
				popupMenu.setVisible(true);
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}

			private void hidePop() {
				popupMenu.setVisible(false);
			}
		});
		
		selectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setSelectionStart(0);
				setSelectionEnd(getText().length());
			}
		});
		
		cutSelection.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					if (getSelectedText().length() > 0) {
						//
					}
				} catch (NullPointerException ee) {
					setSelectionStart(0);
					setSelectionEnd(getText().length());
				}
				StandardRobot.cutAction();
			}
		});
		
		copySelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Copy selected Items to system clipboard
				try {
					if (getSelectedText().length() > 0) {
						//
					}
				} catch (NullPointerException ee) {
					setSelectionStart(0);
					setSelectionEnd(getText().length());
				}
				StandardRobot.copyAction();
			}
		});
		
		pasteClipboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Paste from system clipboard
				StandardRobot.pasteAction();
			}
		});
	}

}
