package MVC;

import DB.Database;
import Model.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private TableReaderModel tableReaderModel;
    private TablePenaltyModel tablePenaltyModel;
    private TableNoticeModel tableNoticeModel;

    public Controller(TableReaderModel tableReaderModel, TablePenaltyModel tablePenaltyModel, TableNoticeModel tableNoticeModel) {
        this.tableReaderModel = tableReaderModel;
        this.tablePenaltyModel = tablePenaltyModel;
        this.tableNoticeModel = tableNoticeModel;
    }

    public void execute(Database database, View view) {
        view.getMenuIReaders().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setTitle("Формирование задолжников - читатели");
                view.setReaders(true);
                try {
                    database.getPenaltyList().clear();
                    database.getNoticeList().clear();
                    view.updateView(tableReaderModel, database);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        view.getMenuIPenalties().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setTitle("Формирование задолжников - штрафы");
                view.setNotReaders(true);
                try {
                    database.getReadersList().clear();
                    database.getNoticeList().clear();
                    view.updateView(tablePenaltyModel, database);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        view.getMenuINotices().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setTitle("Формирование задолжников - уведомления");
                view.setNotReaders(true);
                try {
                    database.getReadersList().clear();
                    database.getPenaltyList().clear();
                    view.updateView(tableNoticeModel, database);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        view.getRadioBReaders().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTableModel().setRowCount(0);
                view.setReaders(true);
                try {
                    database.getReadersList().clear();
                    //database.setReadersList(database.getDebtors());

                    ArrayList<Reader> readerArrayList = database.getReaders();
                    for (int i = 0; i < database.getReadersSize(); i++) {
                        String status;
                        if(readerArrayList.get(i).getID_status() == 1){
                            status = "Читатель";
                        }
                        else{
                            status = "Задолжник";
                        }
                        view.getTableModel().addRow(new Object[]{readerArrayList.get(i).getID_library_card(),
                                readerArrayList.get(i).getFull_name(),
                                status,
                                readerArrayList.get(i).getPhone_number(),
                                readerArrayList.get(i).getEmail(),
                                readerArrayList.get(i).getAddress(),
                                readerArrayList.get(i).getPassport()});
                    }
                    view.getTableModel().fireTableDataChanged();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Try later!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        view.getRadioBDebtors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTableModel().setRowCount(0);
                view.setDebtors(true);
                try {
                    database.getReadersList().clear();
                    //database.setReadersList(database.getDebtors());

                    ArrayList<Reader> readerArrayList = database.getDebtors();
                    for (int i = 0; i < database.getDebtorsSize(); i++) {
                        view.getTableModel().addRow(new Object[]{readerArrayList.get(i).getID_library_card(),
                                readerArrayList.get(i).getFull_name(),
                                "Задолжник",
                                readerArrayList.get(i).getPhone_number(),
                                readerArrayList.get(i).getEmail(),
                                readerArrayList.get(i).getAddress(),
                                readerArrayList.get(i).getPassport()});
                    }
                    view.getTableModel().fireTableDataChanged();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Try later!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        view.getComboBSorting().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = view.getComboBSorting().getSelectedIndex();
                if (view.getTable().getModel() instanceof TableReaderModel) {
                    if(selectedItem == 0){
                        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) view.getTable().getModel());
                        sorter.setSortable(0, true);
                        sorter.setSortsOnUpdates(true);
                        view.getTable().setRowSorter(sorter);
                        sorter.toggleSortOrder(0);
                    }
                    else if (selectedItem == 1){
                        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) view.getTable().getModel());
                        sorter.setSortable(1, true);
                        sorter.setSortsOnUpdates(true);
                        view.getTable().setRowSorter(sorter);
                        sorter.toggleSortOrder(1);
                    }
                }
            }
        });
        view.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    database.updateReaders1();
                    database.updateReaders2();
                    database.updatePenalties();
                    database.insertPenalties();
                    database.insertNotices1();
                    database.insertNotices2();
                    view.getTableModel().setRowCount(0);
                    database.getReadersList().clear();
                    ArrayList<Reader> readerArrayList = database.getDebtors();
                    for (int i = 0; i < database.getDebtorsSize(); i++) {
                        String status;
                        if(readerArrayList.get(i).getID_status() == 1){
                            status = "Читатель";
                        }
                        else{
                            status = "Задолжник";
                        }
                        view.getTableModel().addRow(new Object[]{readerArrayList.get(i).getID_library_card(),
                                readerArrayList.get(i).getFull_name(),
                                status,
                                readerArrayList.get(i).getPhone_number(),
                                readerArrayList.get(i).getEmail(),
                                readerArrayList.get(i).getAddress(),
                                readerArrayList.get(i).getPassport()});
                    }
                    view.getTableModel().fireTableDataChanged();
                    view.getRadioBReaders().setSelected(false);
                    view.getRadioBDebtors().setSelected(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Try later!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}