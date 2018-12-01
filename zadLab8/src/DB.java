import java.sql.*;
import java.util.ArrayList;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String db;
    private String login;
    private String password;

    public DB(String db, String login, String password) throws SQLException{
        this.db = db;
        this.login = login;
        this.password = password;

        for(int i=0; i<3; ++i) {
            try {
                if(connect())
                    break;
            }catch(SQLException ex) {
                if(i==2)
                    throw ex;
            }
        }

    }

    public boolean connect() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/"+ db,
                            login,password);

            return true;

        } catch (SQLException ex) {
            throw ex;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<ArrayList<String>> getRows(String query){

        ArrayList<ArrayList<String>> relation = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            ArrayList<String> titleRow = new ArrayList<>();

            for(int i=1; i<=columns; ++i)
                titleRow.add(rsmd.getColumnName(i));

            relation.add(titleRow);

            while(rs.next()){

                ArrayList<String> row = new ArrayList<>();

                for(int i=1; i<=columns; i++)
                    row.add(rs.getString(i));


                relation.add(row);
            }

        }catch (SQLException ex){
            ex.getErrorCode();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }

        return relation;
    }

    public void addRow(String query){

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException ex){
            ex.getErrorCode();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
    }

}