package it.cnr.contab.ordmag.ordini.bp;

import it.cnr.contab.docamm00.docs.bulk.Fattura_passivaBulk;
import it.cnr.contab.docamm00.docs.bulk.Fattura_passiva_rigaBulk;
import it.cnr.contab.ordmag.ordini.bulk.FatturaOrdineBulk;
import it.cnr.contab.ordmag.ordini.bulk.OrdineAcqConsegnaBulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcess;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.ejb.CRUDComponentSession;
import it.cnr.jada.util.Config;
import it.cnr.jada.util.action.SelezionatoreListaBP;
import it.cnr.jada.util.ejb.EJBCommonServices;
import it.cnr.jada.util.jsp.Button;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public class SelezionatoreVisualOrdinAcqConsListaBP extends SelezionatoreListaBP {
    @Override
    public List<Button> createToolbarList() {
        final List<Button> toolbarList = super.createToolbarList();
        toolbarList.add(new Button(Config.getHandler().getProperties(getClass()), "Toolbar.visualizzaFattura"));
        return toolbarList;
    }

    public boolean isVisualizzaFatturaButtonHidden() {
        return !Optional.ofNullable(getModel())
                .filter(OrdineAcqConsegnaBulk.class::isInstance)
                .map(OrdineAcqConsegnaBulk.class::cast)
                .flatMap(ordineAcqConsegnaBulk -> Optional.ofNullable(ordineAcqConsegnaBulk.getStatoFatt()))
                .filter(s -> s.equalsIgnoreCase(OrdineAcqConsegnaBulk.STATO_FATT_ASSOCIATA_TOTALMENTE))
                .isPresent();
    }

    public Optional<Fattura_passivaBulk> visualizzaFattura(ActionContext actionContext) throws BusinessProcessException {
        final CRUDComponentSession crudComponentSession = (CRUDComponentSession)createComponentSession("JADAEJB_CRUDComponentSession");
        final Optional<OrdineAcqConsegnaBulk> ordineAcqConsegnaBulk = Optional.ofNullable(getModel())
                .filter(OrdineAcqConsegnaBulk.class::isInstance)
                .map(OrdineAcqConsegnaBulk.class::cast);
        if (ordineAcqConsegnaBulk.isPresent()) {
            try {
                final List<FatturaOrdineBulk> findByRigaConsegna = crudComponentSession.find(actionContext.getUserContext(),
                        FatturaOrdineBulk.class,
                        "findByRigaConsegna",
                        ordineAcqConsegnaBulk.get());
                return findByRigaConsegna
                        .stream()
                        .findAny()
                        .map(FatturaOrdineBulk::getFatturaPassivaRiga)
                        .map(Fattura_passiva_rigaBulk::getFattura_passiva);
            } catch (ComponentException|RemoteException e) {
                throw handleException(e);
            }
        }
        return Optional.empty();
    }
}
