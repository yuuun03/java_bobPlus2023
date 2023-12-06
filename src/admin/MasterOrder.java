// 컴퓨터공학부 2022136102 임혜원
// 결제 창이 존재하지 않으므로 orderList 변수 또한 존재 x.
// 관리자 계정 관련 소스가 따로 없는 만큼 별도의 화면 구성 없이 기능 위주로 적혀 있음.

package admin;

public class MasterOrder {
    private String memberID;
    private long orderNumber;
    private String buyerName;
    private long phoneNum;
    private String address;
    private int totalPrice;
    private int deliveryState;

    private void mCheckOrderList(String memberID) {
        // memberID를 사용하여 회원의 주문 목록을 확인
        // 해당 회원의 주문 목록을 조회하고 출력
        System.out.println(memberID + "님의 주문 목록 " + ":");
        // + 주문 목록 출력
        System.out.println("주문 번호 : " + orderNumber + ", 구매자 이름 : " + buyerName + ", 총 가격 : " + totalPrice);
        // 추가적으로 다른 주문 정보 출력 + 회원의 경우 전화번호와 주소를 UserInfo에 저장해두기 때문에 별도로 출력하지 않음.
    }

    // 비회원의 주문 목록을 확인하는 함수
    private void nonCheckOrderList(String orderNumber) {
        // orderNumber를 사용하여 비회원의 주문 목록을 확인
        // 해당 주문 번호에 해당하는 주문 정보를 조회하고 출력
        System.out.println("비회원 주문조회 (주문번호 " + orderNumber + ") " + ":");
        // + 주문 목록 출력 orderList
        System.out.println("구매자 : " + buyerName + ", 전화번호 : " + phoneNum + ", 주소 : " + address);
        // 추가적으로 다른 주문 정보 출력
    }

    // 배송 상태를 변경하는 함수
    private void stateOrderChange(long orderNumber, int newDeliveryState) {
        // orderNumber에 해당하는 주문의 배송 상태를 newDeliveryState로 변경
        // 데이터베이스에서 해당 주문을 찾아 배송 상태를 업데이트해야 함
        System.out.println("Changing delivery state for Order Number " + orderNumber + " to " + newDeliveryState);
        this.deliveryState = newDeliveryState;
        // 상태 변경 완료 메시지 출력 및 사이트 내에 기재된 정보 수정
    }

    // 주문 상태를 출력하는 함수
    private void stateOrderPrint(long orderNumber) {
        // orderNumber에 해당하는 주문의 상태를 출력
        // 데이터베이스에서 해당 주문 정보를 조회하고 출력해야 함
        System.out.println("Order State for Order Number " + orderNumber + ":");
        System.out.println("Buyer: " + buyerName + ", Delivery State: " + deliveryState);
        // 추가적으로 다른 주문 정보 출력
    }

    // 접근자 & 생성자
    public String getMemberID() {
        return memberID;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(int deliveryState) {
        this.deliveryState = deliveryState;
    }
}
