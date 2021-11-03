package com.example.application.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class AnswersService{
    @Autowired
    JdbcTemplate jdbcTemp;

    private final AnswersRepository answersRepository;

    public AnswersService(AnswersRepository repository) {
        this.answersRepository = repository;
    }
    public List<Votes> listAllVotes() {
        return answersRepository.findAll();
    }

    public void saveVotes(Votes answer) {
        answersRepository.save(answer);
    }

    public Votes getVotes(Integer id) {
        return answersRepository.findById(id).get();
    }

    public void deleteVotes(Integer id) {
        answersRepository.deleteById(id);
    }

    public void clearVotes(){
        jdbcTemp.update("TRUNCATE `munhelper`.`votes`;");
    }

    public String checkStatus(){
        List<Object> results = jdbcTemp.query("SELECT * FROM status WHERE committee = ?", new Object[] {"main"}, (rs, rowNum) -> rs.getString("status"));
        System.out.println(results);
        return(results.get(0).toString());

    }

    public void setStatus(String openOrClosed){
        jdbcTemp.execute("UPDATE `munhelper`.`status` SET `status` = '"+openOrClosed+"' WHERE (`committee` = 'main');");
    }

    public int checkForDup(String name){
            List<Object> results = jdbcTemp.query("SELECT * FROM votes WHERE name = ?", new Object[] {name}, (rs, rowNum) -> rs.getString("idvotes"));
            String resultsString = "";
            for(Object x:results) {
                resultsString+=x.toString();
            }
            if(resultsString.equals("")){
                return -1;
            }
            return Integer.parseInt(resultsString);
        }


    public int[] getNumEach(){
        List<Votes> votesList = listAllVotes();
        int yay = 0;
        int nay = 0;
        for(Votes x: votesList){
            if(x.getYay().equals("1")){
                yay++;
            }
            else{
                nay++;
            }
        }
        int[] finalList = new int[2];
        finalList[0] = yay;
        finalList[1] = nay;
        return finalList;
    }

}


