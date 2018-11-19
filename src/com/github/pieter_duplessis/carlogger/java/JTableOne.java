package com.github.pieter_duplessis.carlogger.java;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class JTableOne extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTableOne() {
		setCellSelectionEnabled(true);
		
		JPopupMenu popupMenuAllocations = new JPopupMenu();
		JMenuItem copySelectionAllocations = new JMenuItem("Copy Selection");
		popupMenuAllocations.add(copySelectionAllocations);
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
				if (getSelectedRowCount() == 0) {
					return;
				}
				popupMenuAllocations.setVisible(true);
				popupMenuAllocations.show(e.getComponent(), e.getX(), e.getY());
			}

			private void hidePop() {
				popupMenuAllocations.setVisible(false);
			}
		});
		
		copySelectionAllocations.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Copy selected Items to system clipboard
				StandardRobot.copyAction();
			}
		});
	}
}
