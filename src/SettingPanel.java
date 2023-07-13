package 课程设计_是男人就坚持20秒;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingPanel extends JPanel
{
	public JLabel label;
	public JTextField setting;
	public JButton button;
	public int difficult = 3;

	public SettingPanel()
	{
		label = new JLabel("难度(1~5):");
		setting = new JTextField(Integer.toString(difficult), 5);
		button = new JButton("确定");
		button.addActionListener(new ButtonListener());
		add(label);
		add(setting);
		add(button);
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			difficult = Integer.parseInt(setting.getText());
			if (difficult > 5 || difficult < 0)
			{
				difficult = 3;
				setting.setText("3");
			}
		}
	}
}
