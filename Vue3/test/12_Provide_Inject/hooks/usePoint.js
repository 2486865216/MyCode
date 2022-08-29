import { reactive, onMounted, onBeforeUnmount } from 'vue'
export default function () {
    let point = reactive({
        x: 0,
        y: 0,
    });
    function savepoint(event) {
        point.x = event.pageX;
        point.y = event.pageY;
        console.log(event.pageX, event.pageY);
    }
    onMounted(() => {
        window.addEventListener("click", savepoint);
    });
    onBeforeUnmount(() => {
        window.removeEventListener('click', savepoint)
    })
    return point
}