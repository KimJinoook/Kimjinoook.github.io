package personal;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class Calc extends JFrame {


    public Calc() {
        initComponents();
        addEvent();
    }
    
    

    private void addEvent() {
        bt1.addActionListener(new EventHandler());
        bt2.addActionListener(new EventHandler());
        bt3.addActionListener(new EventHandler());
        bt4.addActionListener(new EventHandler());
        bt5.addActionListener(new EventHandler());
        bt6.addActionListener(new EventHandler());
        bt7.addActionListener(new EventHandler());
        bt8.addActionListener(new EventHandler());
        bt9.addActionListener(new EventHandler());
        btAdd.addActionListener(new EventHandler());
        btBs.addActionListener(new EventHandler());
        btC.addActionListener(new EventHandler());
        btCE.addActionListener(new EventHandler());
        btDiv.addActionListener(new EventHandler());
        btDot.addActionListener(new EventHandler());
        btDou.addActionListener(new EventHandler());
        btDow.addActionListener(new EventHandler());
        btMin.addActionListener(new EventHandler());
        btPer.addActionListener(new EventHandler());
        btRev.addActionListener(new EventHandler());
        btRoo.addActionListener(new EventHandler());
        btRst.addActionListener(new EventHandler());
        btTim.addActionListener(new EventHandler());
        jButton24.addActionListener(new EventHandler());
		
	}
    
    public class EventHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==jButton24 || e.getSource()==bt1 || e.getSource()==bt2 ||
					e.getSource()==bt3 || e.getSource()==bt4 || e.getSource()==bt5 ||
					e.getSource()==bt6 || e.getSource()==bt7 || e.getSource()==bt8 ||
					e.getSource()==bt9 || e.getSource()==btRev) {
				if(!bool) {
					bool = true;
					tf1.setText("");
					tf2.setText("0");
				}
				if(tf2.getText().equals("0")) {
					tf2.setText("");
				}
				
				String str1 = tf2.getText();
				JButton obj = (JButton)e.getSource();
				String str2 = obj.getText();
				tf2.setText(str1+str2);
			}//¼ýÀÚ¹öÆ°
			
			if(e.getSource()==btDot) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str1 = tf2.getText();
				JButton obj = (JButton)e.getSource();
				String str2 = obj.getText();
				tf2.setText(str1+str2);
			}//¼Ò¼öÁ¡
			if(e.getSource()==btAdd) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str1 = tf2.getText();
				String str2 = "+";
				tf2.setText(str1+str2);
				
				String str3 = tf1.getText();
				String str4 = tf2.getText();
				tf1.setText(str3+str4);
				
				tf2.setText("0");
			}
			if(e.getSource()==btMin) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str1 = tf2.getText();
				String str2 = "-";
				tf2.setText(str1+str2);
				
				String str3 = tf1.getText();
				String str4 = tf2.getText();
				tf1.setText(str3+str4);
				
				tf2.setText("0");
			}
			if(e.getSource()==btTim) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str1 = tf2.getText();
				String str2 = "*";
				tf2.setText(str1+str2);
				
				String str3 = tf1.getText();
				String str4 = tf2.getText();
				tf1.setText(str3+str4);
				
				tf2.setText("0");
			}
			if(e.getSource()==btDiv) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str1 = tf2.getText();
				String str2 = "/";
				tf2.setText(str1+str2);
				
				String str3 = tf1.getText();
				String str4 = tf2.getText();
				tf1.setText(str3+str4);
				
				tf2.setText("0");
			}//»çÄ¢¿¬»ê
			if(e.getSource()==btCE) {
				if(!bool) {
					bool = true;
				}
				tf2.setText("0");
			}
			if(e.getSource()==btBs) {
				if(!bool) {
					bool = true;
				}
				String str = tf2.getText();
				str= str.substring(0,str.length()-1);
				tf2.setText(str);
				if(tf2.getText().length()==0) {
					tf2.setText("0");
				}
			}
			if(e.getSource()==btC) {
				if(!bool) {
					bool = true;
				}
				tf2.setText("0");
				tf1.setText("");
			}
			if(e.getSource()==btPer) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str = tf2.getText();
				float f = Float.parseFloat(str)/100;
				str = Float.toString(f);
				tf2.setText(str);

			}

			if(e.getSource()==btRoo) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str = tf2.getText();
				double d = Double.parseDouble(str);
				double d2 = (int)(Math.sqrt(d)*10000)/10000f;
				str = Double.toString(d2);
				int idx = str.lastIndexOf(".");
				String str2 = str.substring(idx);
				System.out.println(str2);
				if(str2.equals(".0")) {
					str = str.substring(0,idx);
				}
				tf2.setText(str);
			}
			if(e.getSource()==btDow) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str = tf2.getText();
				float f = Float.parseFloat(str);
				float f2 = (int)(1/f*10000)/10000f;
				str = Float.toString(f2);
				int idx = str.lastIndexOf(".");
				String str2 = str.substring(idx);
				System.out.println(str2);
				if(str2.equals(".0")) {
					str = str.substring(0,idx);
				}
				tf2.setText(str);
			}
			if(e.getSource()==btDou) {
				if(!bool) {
					bool = true;
					tf1.setText("");
				}
				String str = tf2.getText();
				float f = Float.parseFloat(str);
				float f2 = (int)(f*f*10000)/10000f;
				str = Float.toString(f2);
				int idx = str.lastIndexOf(".");
				String str2 = str.substring(idx);
				System.out.println(str2);
				if(str2.equals(".0")) {
					str = str.substring(0,idx);
				}
				tf2.setText(str);
			}
			if(e.getSource()==btRst) {
				String str3 = tf1.getText();
				String str4 = tf2.getText();
				tf1.setText(str3+str4);
				String rst = operator();
				tf1.setText(str3+str4+"=");
				tf2.setText(rst);
				for(int i = 0 ; i<rst.length();i++) {
					if(rst.substring(i,i+1).equals(".")){
						while(rst.substring(rst.length()-1).equals("0")) {
							rst=rst.substring(0,rst.length()-1);
						}
					}
				}
				
				tf2.setText(rst);
				bool = false;
			}
			
		}//¸Þ¼­µå
    }//Å¬·¡½º
    



	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        tf1 = new JTextField();
        tf2 = new JTextField("0");
        btPer = new JButton();
        btDou = new JButton();
        btRoo = new JButton();
        btDow = new JButton();
        btCE = new JButton();
        btC = new JButton();
        btBs = new JButton();
        btDiv = new JButton();
        btTim = new JButton();
        bt9 = new JButton();
        bt8 = new JButton();
        bt7 = new JButton();
        btMin = new JButton();
        bt6 = new JButton();
        bt5 = new JButton();
        bt4 = new JButton();
        btAdd = new JButton();
        bt3 = new JButton();
        bt2 = new JButton();
        bt1 = new JButton();
        btRev = new JButton();
        btDot = new JButton();
        btRst = new JButton();
        jButton24 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tf1.setEditable(false);
        tf1.setFont(new Font("¸¼Àº °íµñ", 0, 14)); // NOI18N
        tf1.setHorizontalAlignment(JTextField.RIGHT);
        tf1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tf1.setFocusable(false);

        tf2.setEditable(false);
        tf2.setFont(new Font("¸¼Àº °íµñ", 1, 22)); // NOI18N
        tf2.setHorizontalAlignment(JTextField.RIGHT);
        tf2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tf2.setFocusable(false);


        btPer.setBackground(new Color(204, 204, 204));
        btPer.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btPer.setText("%");
        btPer.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btPer.setBorderPainted(false);
        btPer.setCursor(new Cursor( Cursor.DEFAULT_CURSOR));


        btDou.setBackground(new Color(204, 204, 204));
        btDou.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btDou.setText("^2");
        btDou.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btRoo.setBackground(new Color(204, 204, 204));
        btRoo.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btRoo.setText("2¡î");
        btRoo.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btDow.setBackground(new Color(204, 204, 204));
        btDow.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btDow.setText("1/x");
        btDow.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btCE.setBackground(new Color(204, 204, 204));
        btCE.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btCE.setText("CE");
        btCE.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btC.setBackground(new Color(204, 204, 204));
        btC.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btC.setText("C");
        btC.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btBs.setBackground(new Color(204, 204, 204));
        btBs.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btBs.setText("BS");
        btBs.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btDiv.setBackground(new Color(204, 204, 204));
        btDiv.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btDiv.setText("¡À");
        btDiv.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btTim.setBackground(new Color(204, 204, 204));
        btTim.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btTim.setText("¡¿");
        btTim.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt9.setBackground(new Color(254, 254, 254));
        bt9.setFont(new Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt9.setText("9");
        bt9.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt8.setBackground(new Color(254, 254, 254));
        bt8.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt8.setText("8");
        bt8.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt7.setBackground(new  Color(254, 254, 254));
        bt7.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt7.setText("7");
        bt7.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btMin.setBackground(new  Color(204, 204, 204));
        btMin.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btMin.setText("-");
        btMin.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt6.setBackground(new  Color(254, 254, 254));
        bt6.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt6.setText("6");
        bt6.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt5.setBackground(new  Color(254, 254, 254));
        bt5.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt5.setText("5");
        bt5.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt4.setBackground(new  Color(254, 254, 254));
        bt4.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt4.setText("4");
        bt4.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btAdd.setBackground(new  Color(204, 204, 204));
        btAdd.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btAdd.setText("+");
        btAdd.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt3.setBackground(new  Color(254, 254, 254));
        bt3.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt3.setText("3");
        bt3.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt2.setBackground(new  Color(254, 254, 254));
        bt2.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt2.setText("2");
        bt2.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        bt1.setBackground(new  Color(254, 254, 254));
        bt1.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        bt1.setText("1");
        bt1.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btRev.setBackground(new  Color(254, 254, 254));
        btRev.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btRev.setText("000");
        btRev.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btDot.setBackground(new  Color(254, 254, 254));
        btDot.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btDot.setText(".");
        btDot.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btRst.setBackground(new  Color(153, 153, 153));
        btRst.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        btRst.setText("=");
        btRst.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton24.setBackground(new  Color(254, 254, 254));
        jButton24.setFont(new  Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
        jButton24.setText("0");
        jButton24.setBorder( BorderFactory.createEmptyBorder(1, 1, 1, 1));

         GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addComponent(tf1)
            .addComponent(tf2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt4,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt5,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt6,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btMin,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btPer,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btDou,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btRoo,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btDow,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btCE,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btC,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btBs,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btDiv,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt7,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt8,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt9,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btTim,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING)
                            .addComponent(btRev,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt1,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt2,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt3,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton24,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btDot,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(btAdd,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRst,  GroupLayout.PREFERRED_SIZE, 90,  GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tf1,  GroupLayout.PREFERRED_SIZE, 46,  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf2,  GroupLayout.PREFERRED_SIZE, 74,  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addComponent(btPer,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btDou,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRoo,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDow,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btCE,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btC,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btBs,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btDiv,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(bt7,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt8,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt9,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btTim,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(bt4,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt5,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt6,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btMin,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(bt1,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt2,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bt3,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btAdd,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btRev,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btDot,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btRst,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton24,  GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>                        

                                 
    public String operator() {
		char operation[] = {'+','-','*','/'};
		ArrayList <String> post = new ArrayList<>();
		Stack<Character> opStack = new Stack<>();
		Stack<String> calStack = new Stack<>();
		String num = "";
		String str = tf1.getText();
		for(int i=0; i<str.length(); i++) {
			boolean checkOp = false;
			for(int j=0; j<operation.length;j++) {
				if(str.charAt(i)==operation[j]) {
					checkOp = true;
					if(!num.equals("")) {
						post.add(num);
						num="";
					}
					if(opStack.isEmpty()) {
						opStack.push(operation[j]);
					}else {
						if(opCheck(opStack.peek())<opCheck(operation[j])) {
							opStack.push(operation[j]);
						}else {
							post.add(opStack.pop().toString());
							opStack.push(operation[j]);
						}
					}
				}
			}
			if(!checkOp) {
				num += str.charAt(i);
			}
		}
		if(!num.equals("")) {
			post.add(num);
		}
		while(!opStack.isEmpty()) {
			post.add(opStack.pop().toString());
		}
		
		
		for(int i =0;i<post.size(); i++) {
			calStack.push(post.get(i));
			for(int j=0; j<operation.length; j++) {
				if(post.get(i).charAt(0)==operation[j]) {
					calStack.pop();
					Double n2 = Double.parseDouble(calStack.pop());
					String re = "";
					
					Double n1 = Double.parseDouble(calStack.pop());
					if(operation[j]=='+') {
						re = Double.toString(n1+n2);
					}else if(operation[j]=='-') {
						re = Double.toString(n1-n2);
					}else if(operation[j]=='*') {
						re = Double.toString(n1*n2);
					}else if(operation[j]=='/') {
						re = Double.toString(n1/n2);
					}
					calStack.push(re);
				}
			}
		}
		Double result = Double.parseDouble(calStack.pop());
		String calResult[] = Double.toString(result).split("\\.");
		
		if(Double.parseDouble(calResult[1])==0) {
			if(3<=calResult[1].length() && calResult[1].substring(0,2).equals("0E")) {
				return Double.toString(result);
			}else {
				return calResult[0];
			}
		}else {
			return String.format("%.10f", result);
		}
		
	}
    public int opCheck(char op) {
    	switch(op) {
    	case '+' :
    	case '-' :
    		return 1;
    	case '*' :
    	case '/' :
    		return 2;
    	default:
    		return -1;
    	}
    }


	public static void main(String args[]) {

        Calc f = new Calc();
        f.setVisible(true);
        
    }

    // Variables declaration - do not modify                     
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private JButton bt5;
    private JButton bt6;
    private JButton bt7;
    private JButton bt8;
    private JButton bt9;
    private JButton btAdd;
    private JButton btBs;
    private JButton btC;
    private JButton btCE;
    private JButton btDiv;
    private JButton btDot;
    private JButton btDou;
    private JButton btDow;
    private JButton btMin;
    private JButton btPer;
    private JButton btRev;
    private JButton btRoo;
    private JButton btRst;
    private JButton btTim;
    private JButton jButton24;
    private JTextField tf1;
    private JTextField tf2;
    private boolean bool = true;
    // End of variables declaration                   
}
