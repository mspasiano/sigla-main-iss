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

package it.cnr.contab.web.rest.local.util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import it.cnr.contab.client.docamm.FatturaAttiva;
import it.cnr.contab.web.rest.config.AccessoAllowed;
import it.cnr.contab.util.enumeration.AccessoEnum;
import it.cnr.contab.web.rest.config.SIGLASecurityContext;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Local
@Path("/fatture-attive")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("Fatturazione Attiva Elettronica")
public interface PECFattureAttiveLocal {

    @GET
    @Path("/reinvia-pec")
    @AccessoAllowed(value= AccessoEnum.XXXHTTPSESSIONXXXXXX)
    @ApiOperation(value = "Reinvia tramite PEC l'xml della fattura attiva a SDI",
            notes = "Accesso consentito solo alle utenze abilitate con accesso XXXHTTPSESSIONXXXXXX",
            response = Void.class,
            authorizations = {
                    @Authorization(value = "BASIC")
            }
    )
    Response reinviaPEC(@Context HttpServletRequest request, @QueryParam("esercizio") Integer esercizio, @QueryParam("pgFatturaAttiva") Long pgFatturaAttiva) throws Exception;

    @GET
    @Path("/aggiorna-nome-file")
    @AccessoAllowed(value= AccessoEnum.XXXHTTPSESSIONXXXXXX)
    @ApiOperation(value = "Aggiorna il nome del file su tutte le fatture attive con stato INV",
            notes = "Accesso consentito solo alle utenze abilitate con accesso AMMFATTURDOCSFATATTV",
            response = Void.class,
            authorizations = {
                    @Authorization(value = "BASIC")
            }
    )
    Response aggiornaNomeFile(@Context HttpServletRequest request) throws Exception;

    @GET
    @Path("/aggiorna-metadati")
    @AccessoAllowed(value= AccessoEnum.XXXHTTPSESSIONXXXXXX)
    @ApiOperation(value = "Aggiorna i metadati della fattura attiva sul documentale",
            notes = "Accesso consentito solo alle utenze abilitate con accesso AMMFATTURDOCSFATATTV",
            response = Void.class,
            authorizations = {
                    @Authorization(value = "BASIC")
            }
    )
    Response aggiornaMetadati(@Context HttpServletRequest request, @QueryParam("esercizio") Integer esercizio, @QueryParam("cdCds") String cdCds, @QueryParam("pgFatturaAttiva") Long pgFatturaAttiva) throws Exception;

    @GET
    @Path("/reinvia-notifica-ko")
    @AccessoAllowed(value= AccessoEnum.XXXHTTPSESSIONXXXXXX)
    @ApiOperation(value = "Reinvia la notifica di esito negativo a tutte le utenza configurate a ricevere la notifica e all'utenza che ha creato la fattura",
            notes = "Accesso consentito solo alle utenze abilitate con accesso AMMFATTURDOCSFATATTV",
            response = List.class,
            authorizations = {
                    @Authorization(value = "BASIC")
            }
    )
    Response reinviaNotifica(@Context HttpServletRequest request, @QueryParam("esercizio") Integer esercizio, @QueryParam("cdCds") String cdCds, @QueryParam("cdUnitaOrganizzativa")String cdUnitaOrganizzativa, @QueryParam("pgFatturaAttiva") Long pgFatturaAttiva) throws Exception;
}
