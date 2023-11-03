import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * @Author: zaj
 * @Decription:
 * @Date:
 * @Email: 2996922498@qq.com
 */
public class Client {

    public static class FrameAlter extends JFrame{


        public FrameAlter(){
            this.setSize(500,500);
            this.setTitle("修改联系人");
            //设置界面置顶
            this.setAlwaysOnTop(true);
            //设置界面居中
            this.setLocationRelativeTo(null);
            //设置关闭模式
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            this.setLayout(null);
            this.setAlwaysOnTop(true);

        }


    }


    public static class FrameCheck extends JFrame {
        public FrameCheck() {
            this.setSize(500, 500);
            this.setTitle("查看联系人");
            //设置界面置顶
            this.setAlwaysOnTop(true);
            //设置界面居中
            this.setLocationRelativeTo(null);
            //设置关闭模式
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setAlwaysOnTop(true);

            this.setVisible(true);
        }


    }


    public static class FrameDelet extends JFrame {
        public FrameDelet() throws IOException {
            this.setSize(500, 500);
            this.setTitle("删除联系人");
            //设置界面置顶
            this.setAlwaysOnTop(true);
            //设置界面居中
            this.setLocationRelativeTo(null);
            //设置关闭模式
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            this.setAlwaysOnTop(true);
            this.setLayout(null);


        }


        }


    public static class FrameNew extends JFrame {
        public FrameNew() {
            this.setSize(500, 500);
            this.setTitle("新建联系人");
            //设置界面置顶
            this.setAlwaysOnTop(true);
            //设置界面居中
            this.setLocationRelativeTo(null);
            //设置关闭模式
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            this.setAlwaysOnTop(true);
            this.setLayout(null);

        }


    }

    public static class InterfaceJFrame extends JFrame {

        public InterfaceJFrame(){
            initJFrame();
            this.setVisible(true);
            initButton();
        }

        public void initJFrame(){
            this.setSize(600,500);
            this.setTitle("通讯录");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //设置界面置顶
            this.setAlwaysOnTop(true);
            //设置界面居中
            this.setLocationRelativeTo(null);
            //界面显示
            this.setLayout(null);

            File file = new File(".\\src\\contact.txt");
            if(file.length()!=0){
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(".\\src\\contact.txt"));
                    ArrayList<Server.Infro> obj = (ArrayList<Server.Infro>) ois.readObject();
                    Server.Infro.list.addAll(obj);
                } catch (IOException | ClassNotFoundException ex) {ex.printStackTrace();
                }
            }

        }


        public void initButton(){
            JButton bNew = new JButton("新建联系人");//新建
            JButton bCheck = new JButton("查看联系人");//查找
            JButton bDelete = new JButton("删除联系人");//删除
            JButton bAlter = new JButton("修改联系人");//修改

            bNew.setBounds(100,100,150,50);
            bCheck.setBounds(300,100,150,50);
            bDelete.setBounds(100,300,150,50);
            bAlter.setBounds(300,300,150,50);

            bNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   Server server = new Server();
                   server.New();
                }
            });

            bCheck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Server server = new Server();
                    server.Check();
                }
            });

            bDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                    try {
                        server.Delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            bAlter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                    server.Alter();
                }
            });
            add(bNew);
            add(bCheck);
            add(bDelete);
            add(bAlter);
        }
    }

    public static void main(String[] args) {

        new InterfaceJFrame();

    }

}
