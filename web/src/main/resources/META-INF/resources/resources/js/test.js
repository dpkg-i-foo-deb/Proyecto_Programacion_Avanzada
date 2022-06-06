let liked = false;
function favorito () {
    liked = !liked;
    document.getElementById("like-btn-list").classList.add(liked ? 'pi-heart-fill' : 'heart');
}