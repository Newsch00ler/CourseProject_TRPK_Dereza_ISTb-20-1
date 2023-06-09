package MVC;

import DB.Database;
import Model.Notice;
import Model.Penalty;
import Model.Reader;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class View extends JFrame {
    private JMenuBar mB;
    private JMenu mReaders;
    private JMenuItem mIReaders;
    private JMenu mPenalties;
    private JMenuItem mIPenalties;
    private JMenu mNotices;
    private JMenuItem mINotices;

    private JPanel pTop;
    private JLabel lReaders;
    private JRadioButton rbReaders;
    private JLabel lDebtors;
    private JRadioButton rbDebtors;
    private JComboBox<String> cBSorting;
    private JButton button;

    private DefaultTableModel tM;
    private JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Возвращает false, чтобы ячейки были нередактируемыми
        }
    };
    private JScrollPane sP;

    private Font font;

    private String[] itemsSorting = {
            "По билету",
            "По алфавиту"
    };

    private Object currentModel;

    private boolean flag = false;


    public View() throws SQLException {
        setTitle("Формирование задолжников - читатели");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        setIconImage(new ImageIcon(Objects.requireNonNull(classloader.getResource("resources/Icon_book.png"))).getImage());
        font = new Font("TimesRoman", 0, 14);

        mB = new JMenuBar();
        mReaders = new JMenu("Читатели");
        mIReaders = new JMenuItem("Просмотреть");
        mReaders.add(mIReaders);
        mB.add(mReaders);
        mPenalties = new JMenu("Штрафы");
        mIPenalties = new JMenuItem("Просмотреть");
        mPenalties.add(mIPenalties);
        mB.add(mPenalties);
        mNotices = new JMenu("Уведомления");
        mINotices = new JMenuItem("Просмотреть");
        mNotices.add(mINotices);
        mB.add(mNotices);
        mB.add(Box.createHorizontalGlue());
        setJMenuBar(mB);

        pTop = new JPanel();
        lReaders = new JLabel("Читатели");
        pTop.add(lReaders);
        rbReaders = new JRadioButton();
        rbReaders.setSelected(true);
        pTop.add(rbReaders);
        lDebtors = new JLabel("Задолжники");
        pTop.add(lDebtors);
        rbDebtors = new JRadioButton();
        rbDebtors.setSelected(false);
        pTop.add(rbDebtors);
        cBSorting = new JComboBox<>(itemsSorting);
        cBSorting.setFont(font);
        pTop.add(cBSorting);
        button = new JButton("Сформировать список задолжников");
        pTop.add(button);
        pTop.setAlignmentX(0.5F);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        sP = new JScrollPane(table);
        sP.setMaximumSize(new Dimension(800, 600));
        sP.setPreferredSize(new Dimension(800, 600));

        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        add(pTop, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(sP, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public void displayTable(DefaultTableModel model, Database database) throws SQLException {
        if (model instanceof TableReaderModel) {
            if(flag){
                tM.setRowCount(0);
                tM.setColumnCount(0);

            }
            flag = true;
            tM = new TableReaderModel(database);
            table.setModel(tM);
            TableReaderModel tableReaderModel = (TableReaderModel) tM;
            for(int i = 0; i < tableReaderModel.getColumnNameSize(); i++){
                tM.addColumn(tableReaderModel.getColumnName()[i]);
            }
            table.getColumnModel().getColumn(0).setMinWidth(140);
            table.getColumnModel().getColumn(0).setMaxWidth(140);

            table.getColumnModel().getColumn(2).setMinWidth(85);
            table.getColumnModel().getColumn(2).setMaxWidth(85);

            table.getColumnModel().getColumn(3).setMinWidth(95);
            table.getColumnModel().getColumn(3).setMaxWidth(95);

            table.getColumnModel().getColumn(6).setMinWidth(80);
            table.getColumnModel().getColumn(6).setMaxWidth(80);

            ArrayList<Reader> readerArrayList = database.getReaders();
            database.setReadersList(readerArrayList);
//            for (Reader reader : readerArrayList) {
//                String status;
//                if (reader.getID_status() == 1) {
//                    status = "Читатель";
//                } else {
//                    status = "Задолжник";
//                }
//                tM.addRow(new Object[]{reader.getID_library_card(),
//                        reader.getFull_name(),
//                        status,
//                        reader.getPhone_number(),
//                        reader.getEmail(),
//                        reader.getAddress(),
//                        reader.getPassport()});
//            }
            for (Reader reader : database.getReadersList()) {
                String status;
                if (reader.getID_status() == 1) {
                    status = "Читатель";
                } else {
                    status = "Задолжник";
                }
                tM.addRow(new Object[]{reader.getID_library_card(),
                        reader.getFull_name(),
                        status,
                        reader.getPhone_number(),
                        reader.getEmail(),
                        reader.getAddress(),
                        reader.getPassport()});
            }
            cBSorting.removeAllItems();
            for(String i : itemsSorting){
                cBSorting.addItem(i);
            }
        }
        else if (model instanceof TablePenaltyModel) {
            tM.setRowCount(0);
            tM.setColumnCount(0);
            tM = new TablePenaltyModel(database);
            table.setModel(tM);
            TablePenaltyModel tablePenaltyModel = (TablePenaltyModel) tM;
            for(int i = 0; i < tablePenaltyModel.getColumnNameSize(); i++){
                tM.addColumn(tablePenaltyModel.getColumnName()[i]);
            }
            table.getColumnModel().getColumn(0).setMinWidth(32);
            table.getColumnModel().getColumn(0).setMaxWidth(32);

            table.getColumnModel().getColumn(1).setMinWidth(255);
            table.getColumnModel().getColumn(1).setMaxWidth(255);

            table.getColumnModel().getColumn(2).setMinWidth(255);
            table.getColumnModel().getColumn(2).setMaxWidth(255);

            table.getColumnModel().getColumn(3).setMinWidth(255);
            table.getColumnModel().getColumn(3).setMaxWidth(255);

            ArrayList<Penalty> penaltyArrayList = database.getPenalties();
            database.setPenaltyList(penaltyArrayList);
            for (Penalty penalty : database.getPenaltyList())
                tM.addRow(new Object[]{penalty.getID_penalty(),
                        penalty.getID_library_card(),
                        penalty.getName(),
                        penalty.getFine()});
        }
        else if (model instanceof TableNoticeModel) {
            tM.setRowCount(0);
            tM.setColumnCount(0);
            tM = new TableNoticeModel(database);
            table.setModel(tM);
            TableNoticeModel tableNoticeModel  = (TableNoticeModel) tM;
            for(int i = 0; i < tableNoticeModel.getColumnNameSize(); i++){
                tM.addColumn(tableNoticeModel.getColumnName()[i]);
            }
            table.getColumnModel().getColumn(0).setMinWidth(32);
            table.getColumnModel().getColumn(0).setMaxWidth(32);

            table.getColumnModel().getColumn(1).setMinWidth(130);
            table.getColumnModel().getColumn(1).setMaxWidth(130);

            table.getColumnModel().getColumn(2).setMinWidth(733);
            table.getColumnModel().getColumn(2).setMaxWidth(733);

            ArrayList<Notice> noticeArrayList = database.getNotices();
            database.setNoticeList(noticeArrayList);
            for (Notice notice : database.getNoticeList())
                tM.addRow(new Object[]{notice.getID_notice(),
                        notice.getID_library_card(),
                        notice.getMessage()});
        }
    }

    public void updateView(DefaultTableModel model, Database database) throws SQLException {
        displayTable(model, database);
        validate();
        repaint();
    }

    public void setReaders(boolean b) {
        pTop.add(lReaders);
        pTop.add(rbReaders);
        pTop.add(lDebtors);
        pTop.add(rbDebtors);
        pTop.add(cBSorting);
        pTop.add(button);
        rbReaders.setSelected(true);
        rbDebtors.setSelected(false);
    }

    public void setDebtors(boolean b) {
        rbReaders.setSelected(false);
        rbDebtors.setSelected(true);
    }

    public void setNotReaders(boolean b) {
        pTop.remove(lReaders);
        pTop.remove(rbReaders);
        pTop.remove(lDebtors);
        pTop.remove(rbDebtors);
        cBSorting.removeAllItems();
        pTop.remove(cBSorting);
        pTop.remove(button);
    }

    public JMenuBar getMenuB() {
        return mB;
    }
    public JMenu getMenuReaders() {
        return mReaders;
    }
    public JMenuItem getMenuIReaders(){
        return mIReaders;
    }
    public JMenu getMenuPenalties() {
        return mPenalties;
    }
    public JMenuItem getMenuIPenalties(){
        return mIPenalties;
    }
    public JMenu getMenuNotices() {
        return mNotices;
    }
    public JMenuItem getMenuINotices(){
        return mINotices;
    }
    public JPanel getPanelTop() {
        return pTop;
    }
    public JLabel getLabelReaders() {
        return lReaders;
    }
    public JRadioButton getRadioBReaders() {
        return rbReaders;
    }
    public JLabel getLabelDebtors() {
        return lDebtors;
    }
    public JRadioButton getRadioBDebtors() {
        return rbDebtors;
    }
    public JComboBox<String> getComboBSorting() {
        return cBSorting;
    }
    public String getComboBSortingItem0() {
        return cBSorting.getItemAt(0);
    }
    public String getComboBSortingItem1() {
        return cBSorting.getItemAt(1);
    }
    public JButton getButton() {
        return button;
    }
    public DefaultTableModel getTableModel() {
        return tM;
    }
    public JTable getTable() {
        return table;
    }
    public JScrollPane getScrollP() {
        return sP;
    }

    public void setMenuB(JMenuBar mB) {
        this.mB = mB;
    }
    public void setMenuReaders(JMenu mReaders) {
        this.mReaders = mReaders;
    }
    public void setMenuPenalties(JMenu mPenalties) {
        this.mPenalties = mPenalties;
    }
    public void setMenuNotices(JMenu mNotices) {
        this.mNotices = mNotices;
    }
    public void setPanelTop(JPanel pTop) {
        this.pTop = pTop;
    }
    public void setLabelReaders(JLabel lReaders) {
        this.lReaders = lReaders;
    }
    public void setRadioBReaders(JRadioButton rbReaders) {
        this.rbReaders = rbReaders;
    }
    public void setLabelDebtors(JLabel lDebtors) {
        this.lDebtors = lDebtors;
    }
    public void setRadioBDebtors(JRadioButton rbDebtors) {
        this.rbDebtors = rbDebtors;
    }
    public void setComboBSorting(JComboBox<String> cBSorting) {
        this.cBSorting = cBSorting;
    }
    public void setButton(JButton button) {
        this.button = button;
    }
    public void setTable(JTable table) {
        this.table = table;
    }
    public void setScrollP(JScrollPane sP) {
        this.sP = sP;
    }
}
