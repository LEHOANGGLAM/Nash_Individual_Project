export default function StarRatingFormat(props) {
    const number = Math.round(props.number / 0.5) * 0.5;
    //console.log(number);
    let star = [ 1, 2, 3, 4, 5];
    return (
        <>
            {star.map((index) =>
                (number >= index) ? <a href="#" key={index}><span class="ion-ios-star"></span></a> : <a href="#" key={index}><span class="ion-ios-star-outline"></span></a>
            )}
        </>
    )
}
