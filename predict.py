# predict.py
import sys
import math
import joblib
import pandas as pd
import requests

# Load scaler & model đã huấn luyện
scaler = joblib.load("scaler.pkl")
model = joblib.load("model.pkl")

# Tọa độ trung tâm Đà Nẵng
DA_NANG_CENTER = (16.0716, 108.2208)
OSM_API = "https://nominatim.openstreetmap.org/search?format=json&q="

def get_coordinates(address):
    """ Lấy tọa độ từ OpenStreetMap """
    try:
        response = requests.get(OSM_API + address, headers={"User-Agent": "Python Geocoder"})
        data = response.json()
        if data:
            lat = float(data[0]["lat"])
            lon = float(data[0]["lon"])
            return lat, lon
    except Exception as e:
        print(f"Lỗi khi lấy tọa độ: {e}")
    return DA_NANG_CENTER  # Trả về tọa độ Đà Nẵng nếu thất bại

def haversine(lat1, lon1, lat2, lon2):
    """ Tính khoảng cách giữa hai tọa độ bằng công thức Haversine """
    lat1, lon1, lat2, lon2 = map(math.radians, [lat1, lon1, lat2, lon2])
    dlat = lat2 - lat1
    dlon = lon2 - lon1
    a = math.sin(dlat / 2)**2 + math.cos(lat1) * math.cos(lat2) * math.sin(dlon / 2)**2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
    return 6371 * c  # Bán kính Trái Đất (km)

if __name__ == "__main__":
    if len(sys.argv) != 8:
        print("Usage: python predict.py <area> <floors> <bedrooms> <bathrooms> <frontage> <road_width> <address>")
        sys.exit(1)

    # Nhận input từ Java
    area, floors, bedrooms, bathrooms, frontage, road_width, address = sys.argv[1:]

    # Lấy tọa độ từ OpenStreetMap
    lat, lon = get_coordinates(address)

    # Tính khoảng cách đến trung tâm Đà Nẵng
    distance_to_center = haversine(lat, lon, *DA_NANG_CENTER)

    # Chuẩn bị dữ liệu dự đoán
    input_vals = [float(area), int(floors), int(bedrooms), int(bathrooms), float(frontage), float(road_width), distance_to_center]
    columns = ["Diện tích", "Số tầng", "Số phòng ngủ", "Số phòng tắm", "Mặt tiền", "Đường vào", "Khoảng cách đến trung tâm (km)"]

    # Đưa về DataFrame
    X_input = pd.DataFrame([input_vals], columns=columns)

    # Chuẩn hóa và dự đoán
    X_scaled = scaler.transform(X_input)
    pred = model.predict(X_scaled)

    # In ra kết quả để Java đọc
    print(round(pred[0], 0))
