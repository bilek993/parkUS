using System;
using System.Net;
using System.Security.Principal;
using System.Text;
using System.Threading;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using System.Net.Http;


namespace parkus_server.Models
{
    public class BasicAuthenticationAttribute : AuthorizationFilterAttribute
    {
        public override void OnAuthorization(HttpActionContext actionContext)
        {
            if (actionContext.Request.Headers.Authorization == null)
            {
                actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);
            }
            else
            {
                string token = actionContext.Request.Headers.Authorization.Parameter;
                string[] userData = Encoding.UTF8.GetString(Convert.FromBase64String(token)).Split(':');

                if (userData.Length != 2)
                    actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);

                if (UserDetailsVeryfication.VerifyCredentials(userData[0], userData[1]))
                {
                    Thread.CurrentPrincipal = new GenericPrincipal(new GenericIdentity(userData[0]), null);
                }
                else
                {
                    actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);
                }
            }
        }
    }
}