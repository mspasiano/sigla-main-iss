/*
 * Copyright (C) 2019  Consiglio Nazionale delle Ricerche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package it.cnr.contab.coepcoan00.ejb;

import it.cnr.contab.coepcoan00.core.bulk.IDocumentoCogeBulk;
import it.cnr.contab.coepcoan00.core.bulk.Scrittura_partita_doppiaBulk;
import it.cnr.jada.UserContext;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.persistency.PersistencyException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ScritturaPartitaDoppiaFromDocumentoComponentSession extends it.cnr.jada.ejb.CRUDComponentSession {
    void removeScrittura(UserContext userContext, Scrittura_partita_doppiaBulk scrittura) throws ComponentException, PersistencyException, java.rmi.RemoteException;
    List<IDocumentoCogeBulk> getAllDocumentiCogeDaContabilizzare(UserContext param0, Integer param1, String param2) throws it.cnr.jada.comp.ComponentException, PersistencyException, java.rmi.RemoteException;
    void loadScritturePatrimoniali(UserContext param0, List<IDocumentoCogeBulk> param1) throws it.cnr.jada.comp.ComponentException, java.rmi.RemoteException;
    void loadScritturaPatrimoniale(UserContext userContext, IDocumentoCogeBulk documentoCoge) throws it.cnr.jada.comp.ComponentException, java.rmi.RemoteException;
}
