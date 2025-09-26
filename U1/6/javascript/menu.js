try{
    const promesas =[
        fetch('https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=1&pageSize=4'),
        fetch('https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=2&pageSize=4'),
        fetch('https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=3&pageSize=4'),
        fetch('https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=4&pageSize=4')
    ];

    //console.log(promesas);

    let respuestas = await Promise.all(promesas);

    let datos = await Promise.all(respuestas.map(respuesta => respuesta.json()));

    //console.log(datos);

    console.log("ElJust Eat Pizzeria. Menú.")
    datos.forEach(pagina => pagina.records.forEach(pizza => console.log(pizza.nom.padEnd(30) + pizza.preu)));

} catch(e){
    console.log(e);
}




