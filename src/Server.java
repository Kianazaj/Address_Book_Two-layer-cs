import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class Server extends JFrame{
    private Infro infroToFind = null;

    public void Delete() throws IOException {
        JFrame jd = new Client.FrameDelet();

        JTextField textField1 = new JTextField();
        JTextArea textArea1 = new JTextArea("请输入要删除联系人姓名");
        textField1.setBounds(0, 0, 200, 30);
        textArea1.setBounds(200, 0, 200, 30);
        jd.add(textField1);
        jd.add(textArea1);

        JButton DeleteButton = new JButton("确认删除联系人");



        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField1.getText();
                Infro infroToRemove = null;

                for (Infro i : Infro.list) {
                    if (i.getName().equals(name)) {
                        infroToRemove = i;
                        break;
                    }
                }

                if (infroToRemove != null) {
                    Infro.list.remove(infroToRemove);
                    System.out.println("成功删除该联系人");
                    for (Infro infro : Infro.list) {
                        System.out.println(infro.toString());
                    }
                    ObjectOutputStream oos = null;
                    try {
                        oos = new ObjectOutputStream(new FileOutputStream(".\\src\\contact.txt"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 往文件中写入对象
                    try {
                        oos.writeObject(Infro.list);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    System.out.println("该联系人不存在");
                }

                jd.dispose();
            }
        });


        DeleteButton.setBounds(250, 250, 180, 80);
        jd.add(DeleteButton);
        jd.setVisible(true);
    }

    public void Check() {

        JFrame jc= new Client.FrameCheck();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        jc.add(textArea);

        File file = new File(".\\src\\contact.txt");
        if(file.length()!=0){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(".\\src\\contact.txt"))) {
                ArrayList<Infro> obj = (ArrayList<Infro>) ois.readObject();
                StringBuilder sb = new StringBuilder();
                for (Infro infro : obj) {
                    sb.append(infro.toString()).append("\n");
                }
                System.out.println(obj.size());
                textArea.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("还没有保存过联系人");
        }

       jc.setVisible(true);
    }

    public void New() {
        JFrame fn = new Client.FrameNew();

        // 创建一个 JTextField 对象
        JTextField textField1 = new JTextField();
        JTextArea textArea1 = new JTextArea("姓名");
        JTextField textField2 = new JTextField();
        JTextArea textArea2 = new JTextArea("地址");
        JTextField textField3 = new JTextField();
        JTextArea textArea3 = new JTextArea("电话");

        // 设置文本框的位置和大小
        textField1.setBounds(0, 0, 200, 30);
        textArea1.setBounds(200, 0, 50, 30);
        textField2.setBounds(0, 35, 200, 30);
        textArea2.setBounds(200, 35, 50, 30);
        textField3.setBounds(0, 70, 200, 30);
        textArea3.setBounds(200, 70, 50, 30);

        fn.add(textField1);
        fn.add(textArea1);
        fn.add(textField2);
        fn.add(textArea2);
        fn.add(textField3);
        fn.add(textArea3);

        // 创建按钮控件，设置单击事件
        JButton saveButton = new JButton("保存联系人");
        saveButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() != 0 && textField2.getText().length() != 0 && textField3.getText().length() != 0) {
                    Infro infro = new Infro(textField1.getText(), textField2.getText(), textField3.getText());
                    Infro.list.add(infro);
                    ObjectOutputStream oos = null;
                    try {
                        oos = new ObjectOutputStream(new FileOutputStream(".\\src\\contact.txt"));
                        // 往文件中写入对象
                        oos.writeObject(Infro.list);
                        oos.close();
                        System.out.println("联系人已保存至 contact.txt。");
                    } catch (IOException ioe) {
                        System.out.println("保存文件出错：" + ioe.getMessage());
                        ioe.printStackTrace();
                    }
                } else {
                    System.out.println("联系人信息不全，无法添加。");
                }

                fn.dispose();
            }

        });

        saveButton.setBounds(250, 250, 180, 80);
        fn.add(saveButton);
        fn.setVisible(true);
    }


    public void Alter() {
        JFrame fa = new Client.FrameAlter();

        JTextField textField1 = new JTextField();
        JTextArea textArea1 = new JTextArea("请输入要修改的联系人姓名");
        textField1.setBounds(0, 0, 200, 30);
        textArea1.setBounds(200, 0, 200, 30);
        fa.add(textField1);
        fa.add(textArea1);

        JButton findButton = new JButton("查找");
        JButton AlterButton = new JButton("确认修改联系人");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField1.getText();
                for (Infro i : Infro.list) {
                    if (i.getName().equals(name)) {
                        infroToFind = i;
                        break;
                    }
                }

                if(infroToFind!=null)
                {
                    System.out.println("已查找到该联系人");
                    System.out.println(infroToFind.toString());

                    JTextField nameTextField = new JTextField(infroToFind.getName());
                    JTextArea nametextArea = new JTextArea("姓名");
                    nameTextField.setBounds(0,110,200,30);
                    nametextArea.setBounds(200,110,50,30);
                    fa.add(nameTextField);
                    fa.add(nametextArea);

                    JTextField addressTextField = new JTextField(infroToFind.getAddress());
                    JTextArea addresstextArea = new JTextArea("地址");
                    addressTextField.setBounds(0,150,200,30);
                    addresstextArea.setBounds(200,150,50,30);
                    fa.add(addressTextField);
                    fa.add(addresstextArea);

                    JTextField phoneNumberTextField = new JTextField(infroToFind.getPhoneNumber());
                    JTextArea phoneNumbertextArea = new JTextArea("电话");
                    phoneNumberTextField.setBounds(0,190,200,30);
                    phoneNumbertextArea.setBounds(200,190,50,30);
                    fa.add(phoneNumberTextField);
                    fa.add(phoneNumbertextArea);

                    repaint();
                    AlterButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            String name = nameTextField.getText();
                            String address = addressTextField.getText();
                            String phoneNumber = phoneNumberTextField.getText();

                            // 进行联系人信息的修改
                            for(Infro infro : Infro.list){
                                if(infro.getName().equals(infroToFind.getName())){
                                    infro.setName(name);
                                    infro.setAddress(address);
                                    infro.setPhoneNumber(phoneNumber);
                                }
                            }

                            ObjectOutputStream oos = null;
                            try {
                                oos = new ObjectOutputStream(new FileOutputStream(".\\src\\contact.txt"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            // 往文件中写入对象
                            try {
                                oos.writeObject(Infro.list);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                oos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("修改成功");
                            dispose(); // 关闭当前界面
                        }
                    });
                    AlterButton.setBounds(350,350,150,30);
                    fa.add(AlterButton);
                }
                else {
                    System.out.println("没有该联系人");
                }

            }
        });


        findButton.setBounds(0, 35, 70, 70);
        fa.add(findButton);
        fa.setVisible(true);
    }

    public static class Infro implements Serializable{
        private static final long serialVersionUID = 4475483854875567254L;
        public String name;
        public String address;
        public String phoneNumber;
        public static ArrayList<Infro> list = new ArrayList();


        public Infro() {
        }

        public Infro(String name, String address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String toString(){
            return " 联系人姓名："+name+" 通讯地址："+address+" 联系人电话："+phoneNumber;
        }


    }
}
