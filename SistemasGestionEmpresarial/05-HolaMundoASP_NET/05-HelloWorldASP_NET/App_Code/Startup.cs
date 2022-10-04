using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(_05_HelloWorldASP_NET.Startup))]
namespace _05_HelloWorldASP_NET
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
