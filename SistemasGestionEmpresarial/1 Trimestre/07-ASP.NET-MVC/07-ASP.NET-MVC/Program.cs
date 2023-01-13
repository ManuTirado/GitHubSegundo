using _07_ASP.NET_MVC.Controllers;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.MapControllerRoute(
    name: "ListadoPersonas",
    pattern: "ListadoPersonas",
    defaults: new { controller = "Home", Action= "ListadoPersonas" });

app.MapControllerRoute(
    name: "Index",
    pattern: "Index",
    defaults: new { controller = "Home", Action = "Index" });
app.Run();
