package br.ages.crud.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestaProjetoBO.class, TestaProjetoDAO.class, TestaUtil.class, TesteUsuarioDAO.class, TestPontoDAO.class })
public class AllTests {

}
