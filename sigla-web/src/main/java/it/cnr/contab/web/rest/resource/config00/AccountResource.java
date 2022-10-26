/*
 * Copyright (C) 2022  Consiglio Nazionale delle Ricerche
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

package it.cnr.contab.web.rest.resource.config00;

import it.cnr.contab.security.auth.SIGLALDAPPrincipal;
import it.cnr.contab.utenze00.bp.CNRUserContext;
import it.cnr.contab.utenze00.bulk.UtenteBulk;
import it.cnr.contab.web.rest.local.config00.AccountLocal;
import it.cnr.contab.web.rest.model.AccountDTO;
import it.cnr.contab.web.rest.resource.util.AbstractResource;
import it.cnr.jada.ejb.CRUDComponentSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class AccountResource implements AccountLocal {
    private final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);
    @Context
    SecurityContext securityContext;

    @EJB
    private CRUDComponentSession crudComponentSession;

    public AccountDTO getAccountDTO(HttpServletRequest request) throws Exception {
        CNRUserContext userContext = AbstractResource.getUserContext(securityContext, request);
        final Optional<SIGLALDAPPrincipal> siglaldapPrincipal = Optional.ofNullable(securityContext.getUserPrincipal())
                .filter(SIGLALDAPPrincipal.class::isInstance)
                .map(SIGLALDAPPrincipal.class::cast);
        AccountDTO accountDTO = null;
        if (siglaldapPrincipal.isPresent()) {
            final List<UtenteBulk> findUtenteByUID = crudComponentSession.find(
                    userContext,
                    UtenteBulk.class,
                    "findUtenteByUID",
                    userContext,
                    securityContext.getUserPrincipal().getName()
            );
            accountDTO = new AccountDTO(findUtenteByUID.stream().findFirst().get());
            accountDTO.setLogin(siglaldapPrincipal.get().getName());
            accountDTO.setUsers(findUtenteByUID.stream().map(utenteBulk -> new AccountDTO(utenteBulk)).collect(Collectors.toList()));
            accountDTO.setEmail((String) siglaldapPrincipal.get().getAttribute("mail"));
            accountDTO.setFirstName((String) siglaldapPrincipal.get().getAttribute("cnrnome"));
            accountDTO.setLastName((String) siglaldapPrincipal.get().getAttribute("cnrcognome"));
            accountDTO.setLdap(Boolean.TRUE);
            accountDTO.setUtenteMultiplo(findUtenteByUID.size() > 1);
        } else {
            final UtenteBulk utenteBulk = (UtenteBulk) crudComponentSession.findByPrimaryKey(
                    userContext,
                    new UtenteBulk(securityContext
                            .getUserPrincipal()
                            .getName()
                            .toUpperCase()
                    )
            );
            accountDTO = new AccountDTO(utenteBulk);
            accountDTO.setLogin(securityContext.getUserPrincipal().getName());
        }
        accountDTO.setEsercizio(userContext.getEsercizio());
        accountDTO.setCds(userContext.getCd_cds());
        accountDTO.setUo(userContext.getCd_unita_organizzativa());
        accountDTO.setCdr(userContext.getCd_cdr());
        return accountDTO;
    }

    @Override
    public Response get(HttpServletRequest request) throws Exception {
        if (Optional.ofNullable(securityContext.getUserPrincipal()).isPresent())
            return Response.status(Response.Status.OK).entity(getAccountDTO(request)).build();
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @Override
    public Response getUsername(HttpServletRequest request, String username) throws Exception {
        if (Optional.ofNullable(securityContext.getUserPrincipal()).isPresent()) {
            final AccountDTO accountDTO = getAccountDTO(request);
            accountDTO.changeUsernameAndAuthority(username);
            return Response.status(Response.Status.OK).entity(accountDTO).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @Override
    public Response changePassword(HttpServletRequest request, String password) throws Exception {
        return null;
    }
}
