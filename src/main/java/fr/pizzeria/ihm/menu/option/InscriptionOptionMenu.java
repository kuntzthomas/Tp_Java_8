package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.model.Client;

public class InscriptionOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(InscriptionOptionMenu.class);
	private Scanner scanner;
	private IClientDao dao;

	public InscriptionOptionMenu(IClientDao dao) {
		this.dao = dao;
	}

	@Override
	public String getLibelle() {
		return "S'inscrire";
	}

	@Override
	public void execute() throws StockageException, Exception {

		LOG.info("Veuillez saisir votre nom");
		String nom = scanner.next();

		LOG.info("Veuillez saisir votre prenom");
		String prenom = scanner.next();

		LOG.info("Veuillez saisir votre email");
		String email = scanner.next();

		LOG.info("Veuillez saisir votre mot de passe");
		String mdp = DigestUtils.md5Hex(scanner.next());

		Client client = new Client(nom, prenom, email, mdp);
		try {
			dao.saveNew(client);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			LOG.debug("Erreur lors de l'inscription", e);

			LOG.info("Inscription terminée");
		}
	}
}
